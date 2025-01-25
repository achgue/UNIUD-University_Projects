#!/usr/bin/python3
import os
import sys
import json
import base64

def print_alice_to_bob(to_send):
    """
    Prints the data Alice wants to send to Bob into a file named 'output_alice_to_bob.txt'.
    It also prints the garbled circuit in a JSON file named 'alice_circuit_produced.json'
    
    Arguments:
    to_send -- dictionary containing the data to be sent, which includes the circuit details, garbled tables, and output ports
    
    Returns:
    None
    """
    garbled_file = "intermediate-outputs/alice_circuit_produced.json"
    
    if not os.path.exists(garbled_file):
        with open(garbled_file, 'w') as garbled:
            pass  # To make the file empty
    
    with open(garbled_file, 'w') as json_file:
        dictionary = convert_bytes_to_string(to_send["garbled_tables"])
        json.dump(dictionary, json_file, indent=4)
    
    filename = 'intermediate-outputs/output_alice_to_bob.txt'

    # If filename does not exist create it
    if not os.path.exists(filename):
        with open(filename, 'w') as file:
            pass  # To make the file empty

    with open(filename, 'w') as file:
        file.write(f'=================================================\n')
        file.write(f'CIRCUIT\n')
        file.write(f'-------\n')
        file.write(f'id : {to_send["circuit"]["id"]}\nalice input gates :\n\t{to_send["circuit"]["alice"]}\nbob input gates :\n\t{to_send["circuit"]["bob"]}\noutput gates :\n\t{to_send["circuit"]["out"]}\ngates :')
        for item in to_send["circuit"]["gates"]:
            file.write(f"\n\t{item}")
        file.write(f'\n')
        file.write(f'=================================================\n\n')
        file.write(f'=================================================\n')
        file.write(f'GARBLED TABLES\n')
        file.write(f'--------------')
        for item in to_send["garbled_tables"]:
            file.write(f"\n{item}:\n\t")
            for pair in to_send["garbled_tables"][item]:
                file.write(f"\n\t{pair}: ")
                file.write(str(to_send["garbled_tables"][item][pair]))
        file.write(f'\n')
        file.write(f'=================================================\n\n')
        file.write(f'=================================================\n')
        file.write(f'OUTPUT PORTS\n')
        file.write(f'------------\n')
        file.write(f'{to_send["pbits_out"]}\n')
        file.write(f'=================================================')

def alice_bob_OT(msg,p):
    """
    Appends a message related to the Oblivious Transfer (OT) between Alice and Bob to a file.
    
    Arguments:
    msg -- string message to be written to the file
    p -- string indicating the party ("alice" or "bob")
    
    Returns:
    None
    """
    filename = f"intermediate-outputs/ot-communications/{p}_ot.txt"
    with open(filename, 'a') as f:
        f.write(f"{msg}\n")

def bob_alice_mpc_computed(result):
    """
    Prints the result computed by Bob using the combined data in Yao's protocol.
    
    Arguments:
    result -- list of bits representing the output ports
    
    Returns:
    None
    """
    out_port = [int(i) for i in result]
    out_values = [int(result[i]) for i in out_port]
    match_bit = out_values.pop()
    print(f"Result computed according to Yao’s protocol:\n\t- match bit value is {match_bit}\n\t- common number bits are {out_values}")

def verfiy_output(computedResult):
    """
    Verifies the output from Bob's MPC computation against a non-multiparty computed result.
    
    Arguments:
    computedResult -- integer representing the result computed by Bob's MPC
    
    Returns:
    1 if the results match, 0 otherwise
    """
    
    print(f"\n============ VERIFYING OUTPUT ============\n")
    alice_numbers = readInput("alice")
    bob_numbers = readInput("bob")
    common_element = 0
    for element in alice_numbers:
        if element in bob_numbers:
            common_element = element
            break
    if computedResult == common_element:
        return 1
    else:
        return 0
    
    
#-----------------ADDITIONAL FUNCTIONS---------------------


def convert_tuple_to_string(key):
    """
    Converts tuple keys to strings.
    
    Arguments:
    key -- the key to convert (can be a tuple)
    
    Returns:
    The key converted to a string if it was a tuple, otherwise returns the key unchanged.
    """
    if isinstance(key, tuple):
        return str(key)  # Convert tuple key to string
    return key

def convert_bytes_to_string(obj):
    """
    Recursively converts bytes objects in a dictionary to Base64 encoded strings.

    Arguments:
    obj -- the object to convert (can be a dictionary, list, bytes, or other types)

    Returns:
    The object with bytes converted to Base64 encoded strings.
    """
    if isinstance(obj, dict):
        return {convert_tuple_to_string(key): convert_bytes_to_string(value) for key, value in obj.items()}
    elif isinstance(obj, list):
        return [convert_bytes_to_string(element) for element in obj]
    elif isinstance(obj, bytes):
        return base64.b64encode(obj).decode('utf-8')  # Convert bytes to Base64 string
    else:
        return obj

def intToBinVec(numbers):
    """
    Converts a list of integers to their binary representation in a 4-bit format.
    
    Arguments:
    numbers -- list of integers (each integer should be between 0 and 15 inclusive)
    
    Returns:
    bin_numbers -- list of strings representing the 4-bit binary form of each integer in the input list
    """
    bin_numbers = [format(num, '04b') for num in numbers]
    return bin_numbers

def binToInt(bin_number):
    """
    Converts a binary string to its integer representation.

    Arguments:
    bin_number -- string representing a binary number

    Returns:
    integer value of the binary number
    """
    return int(bin_number, 2)


def binToWire(bin_vector):
    """
    Converts a list of binary strings into a list of integers representing the individual bits.
    
    Arguments:
    bin_vector -- list of binary strings
    
    Returns:
    bin_vector -- list of integers where each integer is a bit (0 or 1) from the input binary strings
    """
    temp = []
    for i in range(len(bin_vector)):
        for char in bin_vector[i]:
            temp.append(char)
    bin_vector = temp
    bin_vector = [int(i) for i in bin_vector] #Convert strings in integers
    return bin_vector

def readInput(party):
    """
    Reads a file containing four space-separated integers, validates them, and returns them as a list.
    
    Arguments:
    party -- string representing the party (used to construct the file name)
    
    Returns:
    numbers -- list of four integers read from the file
    
    Raises:
    Prints warning if file does not exist or if the file does not contain exactly 4 numbers,
    or if the numbers are not in the range 0-15.
    """
    numbers =[]
    party_file = f'{party}.txt'
    
    if not os.path.exists(party_file):
        print(f"\033[93mWarning: {party_file} does not exist. Create file {party_file} and insert 4 numbers in decimal coding.\nEach of them has to be in the range 0-15.\033[0m")
        sys.exit()
    
    with open(party_file, 'r') as file:
        line = file.readline().strip()
        numbers_str = line.split()
        numbers = [int(num) for num in numbers_str]
        if len(numbers) != 4:
            print(f"\033[93mWarning: {party_file} has not been set up correctly. Insert 4 numbers in decimal coding whose binary coding is at most 4 bits long.\033[0m")
            sys.exit()
    for num in numbers:
        if num > 15 or num < 0:
            print(f"\033[93mWarning: {party_file} has not been set up correctly. Numbers have to be in the range 0-15 in order to have a correct 4 bit conversion.\033[0m")
            sys.exit()
    return numbers
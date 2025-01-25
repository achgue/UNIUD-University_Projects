#!/usr/bin/env python3
import logging
import ot
import util
import yao
from abc import ABC, abstractmethod

#Importing necessary functions form "requirements.py"
from requirements import print_alice_to_bob, verfiy_output, readInput, intToBinVec, binToInt, binToWire

logging.basicConfig(format="[%(levelname)s] %(message)s",
                    level=logging.WARNING)


class YaoGarbler(ABC):
    """An abstract class for Yao garblers (e.g. Alice)."""
    def __init__(self, circuits):
        circuits = util.parse_json(circuits)
        self.name = circuits["name"]
        self.circuits = []

        for circuit in circuits["circuits"]:
            garbled_circuit = yao.GarbledCircuit(circuit)
            pbits = garbled_circuit.get_pbits()
            entry = {
                "circuit": circuit,
                "garbled_circuit": garbled_circuit,
                "garbled_tables": garbled_circuit.get_garbled_tables(),
                "keys": garbled_circuit.get_keys(),
                "pbits": pbits,
                "pbits_out": {w: pbits[w]
                              for w in circuit["out"]},
            }
            self.circuits.append(entry)

    @abstractmethod
    def start(self):
        pass


class Alice(YaoGarbler):
    """Alice is the creator of the Yao circuit.

    Alice creates a Yao circuit and sends it to the evaluator along with her
    encrypted inputs. Alice will finally print the truth table of the circuit
    for all combination of Alice-Bob inputs.

    Alice does not know Bob's inputs but for the purpose
    of printing the truth table only, Alice assumes that Bob's inputs follow
    a specific order.

    Attributes:
        circuits: the JSON file containing circuits
        oblivious_transfer: Optional; enable the Oblivious Transfer protocol
            (True by default).
    """
    def __init__(self, circuits, oblivious_transfer=True):
        super().__init__(circuits)
        self.socket = util.GarblerSocket()
        self.ot = ot.ObliviousTransfer(self.socket, enabled=oblivious_transfer)

    def start(self):
        """Start Yao protocol."""
        
        print(f"\n============ READING ALICE INPUT ============\n")
        
        #Callinig readInput to read inputs from alice.txt
        self.set = readInput("alice")
        print(f"Input read from Alice's file : {self.set}")
        
        
        print(f"\n============ STARTING YAO PROTOCOL ============\n")
        
        # Initialize/create an OT file to write new OT communication (alice side)
        filename = "intermediate-outputs/ot-communications/alice_ot.txt"
        with open(filename, 'w'):
            pass  # To make the file empty
        print(f"Initialized Alice's OT log file at the path {filename}")
        
        
        for circuit in self.circuits:
            to_send = {
                "circuit": circuit["circuit"],
                "garbled_tables": circuit["garbled_tables"],
                "pbits_out": circuit["pbits_out"],
            }
            logging.debug(f"Sending {circuit['circuit']['id']}")
            
            # Calling print_alice_to_bob to print data alice is sending to bob in "intermediate-outputs/output_alice_to_bob.txt"
            print_alice_to_bob(to_send)
            
            self.socket.send_wait(to_send)
            self.print(circuit)

    def print(self, entry):
        """Print circuit evaluation for all Bob and Alice inputs.

        Args:
            entry: A dict representing the circuit to evaluate.
        """
        circuit, pbits, keys = entry["circuit"], entry["pbits"], entry["keys"]
        
        outputs = circuit["out"]
        a_wires = circuit.get("alice", [])  # Alice's input connections to the circuit
        a_inputs = {}  # map from Alice's wires to (key, encr_bit) inputs
        b_wires = circuit.get("bob", [])  # Bob's wires
        b_keys = {  # map from Bob's wires to a pair (key, encr_bit)
            w: self._get_encr_bits(pbits[w], key0, key1)
            for w, (key0, key1) in keys.items() if w in b_wires
        }
        
        # Converting alice input in binary
        alice_bits = intToBinVec(self.set)
        alice_bits = binToWire(alice_bits)
        
        # Map Alice's wires to (key, encr_bit)
        for i in range(len(a_wires)):
            a_inputs[a_wires[i]] = (keys[a_wires[i]][alice_bits[i]],
                                    pbits[a_wires[i]] ^ alice_bits[i])
        
        print(f"\n============ WAITING FOR RESULT COMPUTATION OF {circuit['id']} ============\n")

        # Send Alice's encrypted inputs and keys to Bob
        result = self.ot.get_result(a_inputs, b_keys)        


        match_bit = result[334] #saving the value of the match bit (which comes out from the port 334 of the circuit)
        
        if match_bit == 0:
            print("There are no matching numbers between the two sets.")
        else:
            str_bits_a = ' '.join(str(alice_bits[:len(a_wires)]))
            str_result = ' '.join([str(result[w]) for w in outputs])
            
            self.result = binToInt(str_result.replace(" ", "")[:-1])  #result converted in decimal base, i remove white spaces and the "mach_bit"
            
            if verfiy_output(self.result) == 1:
                print(f"\033[92mThe comparison between sets was successful. The common element is {self.result}.\033[0m")
            else:
                print(f"\033[33mAn error occured during MPC calculation, however the obtained result is {self.result}, which is not correct.\033[0m")
            
        
        print(f"\n============ END OF COMMUNICATION ============\n")

    def _get_encr_bits(self, pbit, key0, key1):
        return ((key0, 0 ^ pbit), (key1, 1 ^ pbit))


class Bob:
    """Bob is the receiver and evaluator of the Yao circuit.

    Bob receives the Yao circuit from Alice, computes the results and sends
    them back.

    Args:
        oblivious_transfer: Optional; enable the Oblivious Transfer protocol
            (True by default).
    """
    def __init__(self, oblivious_transfer=True):
        self.socket = util.EvaluatorSocket()
        self.ot = ot.ObliviousTransfer(self.socket, enabled=oblivious_transfer)

    def listen(self):
        """Start listening for Alice messages."""
        
        print(f"\n============ READING BOB INPUT ============\n")
        
        #Taking Bob input
        self.set = readInput("bob")
        print(f"Input read from Bob's file : {self.set}")
        
        
        print(f"\n============ STARTING LISTENING FOR MESSAGES ============\n")
        logging.info("Start listening")
        
        
        # Initialize/create an OT file to write new OT communication (bob side)
        filename = "intermediate-outputs/ot-communications/bob_ot.txt"
        with open(filename, 'w'):
            pass  # To make the file empty
        print(f"Initialized Bob's OT log file at the path {filename}")
        
        
        for entry in self.socket.poll_socket():
            self.socket.send(True)
            self.send_evaluation(entry)
            print(f"\n============ END OF COMMUNICATION ============\n")
            break

    def send_evaluation(self, entry):
        """Evaluate yao circuit for all Bob and Alice's inputs and
        send back the results.

        Args:
            entry: A dict representing the circuit to evaluate.
        """
        circuit, pbits_out = entry["circuit"], entry["pbits_out"]
        garbled_tables = entry["garbled_tables"]
        b_wires = circuit.get("bob", [])  # list of Bob's wires

        print(f"\n============ INITIALIZING CIRCUIT COMPUTATION ============\n")

        print(f"Received {circuit['id']}")
        
        # Converting bob input in binary
        bob_bits = intToBinVec(self.set)
        bob_bits = binToWire(bob_bits)    

        # Create dict mapping each wire of Bob to Bob's input
        b_inputs_clear = {
            b_wires[i]: bob_bits[i]
            for i in range(len(b_wires))
        }
        
        # Evaluate and send result to Alice
        result = self.ot.send_result(circuit, garbled_tables, pbits_out,
                            b_inputs_clear)
        
        match_bit = result[334] #saving the value of the match bit (which comes out from the port 334 of the circuit)
        
        if match_bit == 0:
            print("There are no matching numbers between the two sets.")
        else:
            outputs = circuit["out"]        
            str_result = ' '.join([str(result[w]) for w in outputs])
            
            self.result = binToInt(str_result.replace(" ", "")[:-1])  #result converted in decimal base, i remove white spaces and the "mach_bit"
        
            if verfiy_output(self.result) == 1:
                print(f"\033[92mThe comparison between sets was successful. The common element is {self.result}.\033[0m")
            else:
                print(f"\033[33mAn error occured during MPC calculation, however the obtained result is {self.result}, which is not correct.\033[0m")
            
        
    def printResult(self):
        """Function that permits Bob to print the computed result, it waits until Alice sends the final result to him and then print it out
        
        """
        print(self.socket.receive())

def main(
    party,
    circuit_path="circuits/default.json",
    oblivious_transfer=True,
    print_mode="circuit",
    loglevel=logging.WARNING,
):
    logging.getLogger().setLevel(loglevel)

    if party == "alice":
        alice = Alice(circuit_path, oblivious_transfer=oblivious_transfer)
        alice.start()
    elif party == "bob":
        bob = Bob(oblivious_transfer=oblivious_transfer)
        bob.listen()
    else:
        logging.error(f"Unknown party '{party}'")
        


if __name__ == '__main__':
    import argparse

    def init():
        loglevels = {
            "debug": logging.DEBUG,
            "info": logging.INFO,
            "warning": logging.WARNING,
            "error": logging.ERROR,
            "critical": logging.CRITICAL
        }

        parser = argparse.ArgumentParser(description="Run Yao protocol.")
        parser.add_argument("party",
                            choices=["alice", "bob"],
                            help="the yao party to run")
        parser.add_argument(
            "-c",
            "--circuit",
            metavar="circuit.json",
            default="circuits/default.json",
            help=("the JSON circuit file for alice"),
        )
        parser.add_argument("--no-oblivious-transfer",
                            action="store_true",
                            help="disable oblivious transfer")
        parser.add_argument(
            "-m",
            metavar="mode",
            choices=["circuit", "table"],
            default="circuit",
            help="the print mode for local tests (default 'circuit')")
        parser.add_argument("-l",
                            "--loglevel",
                            metavar="level",
                            choices=loglevels.keys(),
                            default="warning",
                            help="the log level (default 'warning')")

        main(
            party=parser.parse_args().party,
            circuit_path=parser.parse_args().circuit,
            oblivious_transfer=not parser.parse_args().no_oblivious_transfer,
            print_mode=parser.parse_args().m,
            loglevel=loglevels[parser.parse_args().loglevel],
        )

    init()
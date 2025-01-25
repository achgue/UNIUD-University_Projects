import hashlib
import logging
import pickle
import util
import yao
from requirements import alice_bob_OT, bob_alice_mpc_computed


class ObliviousTransfer:
    def __init__(self, socket, enabled=True, group=None):
        self.socket = socket
        self.enabled = enabled
        self.group = group
        

    def get_result(self, a_inputs, b_keys):
        """Send Alice's inputs and retrieve Bob's result of evaluation.

        Args:
            a_inputs: A dict mapping Alice's wires to (key, encr_bit) inputs.
            b_keys: A dict mapping each Bob's wire to a pair (key, encr_bit).

        Returns:
            The result of the yao circuit evaluation.
        """
        self.socket.send_wait(a_inputs)
        alice_bob_OT("=========================================================================","alice")
        alice_bob_OT("========================= ALICE OT COMUNICATION =========================","alice")
        alice_bob_OT("=========================================================================","alice")
        alice_bob_OT("Sending inputs to Bob","alice")

        alice_bob_OT("Generating prime group to use for OT","alice")
        self.group = self.enabled and (self.group or util.PrimeGroup())
        alice_bob_OT("Sending prime group\n=========================================================================","alice")
        self.socket.send(self.group)

        for _ in range(len(b_keys)):
            w = self.socket.receive()  # receive gate ID where to perform OT
            alice_bob_OT(f"Received request for gate ID {w}","alice")

            if self.enabled:  # perform oblivious transfer
                pair = (pickle.dumps(b_keys[w][0]), pickle.dumps(b_keys[w][1]))
                alice_bob_OT(f"Sending pair \n\tkey0: {pair[0]} \n\tkey1: {pair[1]}","alice")
                self.ot_garbler(pair)
            else:
                to_send = (b_keys[w][0], b_keys[w][1])
                self.socket.send(to_send)
        result = self.socket.receive()
        
        # Printing the result recived from Bob
        bob_alice_mpc_computed(result)
        return result

    def send_result(self, circuit, g_tables, pbits_out, b_inputs):
        """Evaluate circuit and send the result to Alice.

        Args:
            circuit: A dict containing circuit spec.
            g_tables: Garbled tables of yao circuit.
            pbits_out: p-bits of outputs.
            b_inputs: A dict mapping Bob's wires to (clear) input bits.

        Returns:
            The result of the yao circuit evaluation.
        """
        # map from Alice's wires to (key, encr_bit) inputs
        a_inputs = self.socket.receive()
        self.socket.send(True)
        # map from Bob's wires to (key, encr_bit) inputs
        b_inputs_encr = {}
        alice_bob_OT("=========================================================================","bob")
        alice_bob_OT("========================== BOB OT COMUNICATION ==========================","bob")
        alice_bob_OT("=========================================================================","bob")
        
        alice_bob_OT("Received Alice's inputs","bob")

        self.group = self.socket.receive()
        alice_bob_OT("Received group to use for OT\n=========================================================================","bob")

        for w, b_input in b_inputs.items():
            logging.debug(f"Sending gate ID {w}","bob")
            self.socket.send(w)
            
            if self.enabled:
                b_inputs_encr[w] = pickle.loads(self.ot_evaluator(b_input))
            else:
                pair = self.socket.receive()
                logging.debug(f"Received key pair, key {b_input} selected","bob")
                b_inputs_encr[w] = pair[b_input]

        result = yao.evaluate(circuit, g_tables, pbits_out, a_inputs,
                              b_inputs_encr)
        
        # Printing the result computed by Bob using the combined data in Yao's protocol
        bob_alice_mpc_computed(result)
        
        alice_bob_OT("Sending circuit evaluation","bob")
        self.socket.send(result)
        return result

    def ot_garbler(self, msgs):
        """Oblivious transfer, Alice's side.

        Args:
            msgs: A pair (msg1, msg2) to suggest to Bob.
        """
        alice_bob_OT("-- ALICE OT PROTOCOL STARTED --","alice")
        G = self.group
        # OT protocol based on Nigel Smart’s "Cryptography Made Simple"
        c = G.gen_pow(G.rand_int())
        alice_bob_OT(f"Sending to Bob\n\tc: {c}","alice")
        h0 = self.socket.send_wait(c)
        h1 = G.mul(c, G.inv(h0))
        k = G.rand_int()
        c1 = G.gen_pow(k)
        e0 = util.xor_bytes(msgs[0], self.ot_hash(G.pow(h0, k), len(msgs[0])))
        e1 = util.xor_bytes(msgs[1], self.ot_hash(G.pow(h1, k), len(msgs[1])))
        alice_bob_OT(f"Sending to Bob\n\tc1: {c1}\n\te0: {e0}\n\te1: {e1}","alice")
        self.socket.send((c1, e0, e1))
        alice_bob_OT("-- ALICE OT PROTOCOL ENDED --\n=========================================================================","alice")

    def ot_evaluator(self, b):
        """Oblivious transfer, Bob's side.

        Args:
            b: Bob's input bit used to select one of Alice's messages.

        Returns:
            The message selected by Bob.
        """
        alice_bob_OT("-- BOB OT PROTOCOL STARTED --","bob")
        G = self.group
        alice_bob_OT(f"Sending to OT bit choice\n\tb: {b}","bob")

        # OT protocol based on Nigel Smart’s "Cryptography Made Simple"
        c = self.socket.receive()
        x = G.rand_int()
        x_pow = G.gen_pow(x)
        h = (x_pow, G.mul(c, G.inv(x_pow)))
        c1, e0, e1 = self.socket.send_wait(h[b])
        alice_bob_OT(f"Reciving from Alice\n\tc1: {c1}\n\te0: {e0}\n\te1: {e1}","bob")
        e = (e0, e1)
        ot_hash = self.ot_hash(G.pow(c1, x), len(e[b]))
        mb = util.xor_bytes(e[b], ot_hash)

        alice_bob_OT("-- BOB OT PROTOCOL ENDED --\n=========================================================================","bob")
        return mb

    @staticmethod
    def ot_hash(pub_key, msg_length):
        """Hash function for OT keys."""
        key_length = (pub_key.bit_length() + 7) // 8  # key length in bytes
        bytes = pub_key.to_bytes(key_length, byteorder="big")
        return hashlib.shake_256(bytes).digest(msg_length)
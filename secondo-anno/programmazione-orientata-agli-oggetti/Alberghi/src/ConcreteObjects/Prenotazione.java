package ConcreteObjects;

import lombok.Getter;

/**
 * Rappresenta la prenotazione effettuata da un
 * cliente per una determinata stanza.
 * L'oggetto è immutabile.
 * Permette di calcolare il prezzo previsto per
 * la prenotazione.
 */
public class Prenotazione {
    @Getter
    private final Cliente cliente;
    @Getter
    private final int numeroPersone;
    @Getter
    private final Stanza stanzaPrenotata;


    public Prenotazione(Cliente cliente, int numeroPersone, Stanza stanzaPrenotata) {
        this.cliente = cliente;
        this.numeroPersone = numeroPersone;
        this.stanzaPrenotata = stanzaPrenotata;
    }

    public int getPrezzoPrevisto() {
        return stanzaPrenotata.getPrezzoPerPersona() * numeroPersone;
    }
}

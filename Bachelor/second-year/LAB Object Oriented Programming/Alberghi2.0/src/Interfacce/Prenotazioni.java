package Interfacce;

/**
 * Gestisce le prenotazioni presenti, future e passate.
 * Permette di verificare:
 * la disponibilità di una stanza,
 * modificare lo stato delle prenotazioni (es. da presenti a passate)
 */
public interface Prenotazioni {
    /**
     * Verifica che una determinata stanza sia disponibile in un determinato periodo
     * @param periodo periodo per cui si vuole verificare la disponibilità
     * @return
     */
    public boolean disponibile(Stanza stanza, Periodo periodo);

    public int calcolaPrezzoPrevisto(Prenotazione prenotazione);
}

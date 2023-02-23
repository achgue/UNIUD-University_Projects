package Interfacce;

import java.util.Collection;

/**
 * Gestisce gli sconti e i clienti fedeli
 */
public interface Sconto {
    /**
     * Calcola lo sconto di una prenotazione in base al fatto
     * che il cliente sia fedele o meno
     * @param prenotazione
     * @return
     */
    public int calcolaSconto(Prenotazione prenotazione);

    /**
     *
     * @return i clienti considerati fedeli
     */
    public Collection<Cliente> ottieniClientiFedeli();

    /**
     * Aggiunge un cliente alla whitelist dei fedeli
     */
    public void aggiungiClienteFedele();
}

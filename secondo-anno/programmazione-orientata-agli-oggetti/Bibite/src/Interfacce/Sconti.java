package Interfacce;

import Oggetti.Cliente;
import Oggetti.Ordine;
import lombok.NonNull;

import java.util.Collection;

public interface Sconti {

    /**
     * Calcola lo sconto per un relativo ordine
     * @param ordine per cui si vuole ottenere lo sconto
     * @return sconto sull'ordine
     */
    public int calcolaSconto(@NonNull Ordine ordine);

    /**
     * Permette di ottenere i clienti considerati fedeli dall'azienda
     * @return
     */
    public Collection<Cliente> ottieniClientiFedeli();

    /**
     * Aggiunge un cliente ai clienti fedeli
     * @param cliente
     */
    public void aggiungiClienteFedele(Cliente cliente);
}

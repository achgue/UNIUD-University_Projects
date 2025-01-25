package Interfacce;

import Oggetti.Ordine;
import Oggetti.PiattiTavolo;

public interface RegistroOrdini {

    /**
     * Crea un ordine con determinati piatti
     * @param piattiTavolo piatti ordinati
     * @return l'ordine effettuato
     */
    public Ordine creaOrdine(PiattiTavolo piattiTavolo);

    /**
     * Verifica che tutti gli i piatti siano stati consegnati e modifica lo stato dell'ordine
     */
    public void modificaStatoOrdine();

}

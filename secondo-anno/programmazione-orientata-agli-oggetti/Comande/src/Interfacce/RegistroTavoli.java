package Interfacce;

import Eccezioni.OrdineGiaAssociatoException;
import Eccezioni.TavoloGiaEsistenteException;
import Eccezioni.TavoloSenzaOrdiniException;
import Oggetti.Ordine;
import Oggetti.Tavolo;

public interface RegistroTavoli {

    /**
     * Associa ad un determinato tavolo un determinato ordine
     * @param tavolo
     * @param ordine
     * @throws OrdineGiaAssociatoException se lo stesso ordine è gia stato associato in precedenza
     */
    public void associaOrdineTavolo(Tavolo tavolo, Ordine ordine) throws OrdineGiaAssociatoException;

    /**
     * Calcola il conto per un determinato tavolo
     * @param tavolo tavolo per cui calcolare il conto
     * @return prezzo del conto in euro
     * @throws TavoloSenzaOrdiniException se per il tavolo non ci sono ordini effettuati
     */
    public int calcolaConto(Tavolo tavolo) throws TavoloSenzaOrdiniException;

    /**
     * Aggiunge un tavolo al registro dei tavoli
     * @param tavolo
     * @throws TavoloGiaEsistenteException se il tavolo è stato già inserito nel registro
     */
    public void aggiungiTavolo(Tavolo tavolo) throws TavoloGiaEsistenteException;
}

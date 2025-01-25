package Interfacce;

import Eccezioni.NessunOrdineException;
import Oggetti.Cliente;
import Oggetti.Consegna;
import Oggetti.Ordine;

import java.time.LocalDate;
import java.util.Collection;

public interface RegistroOrdini {

    /**
     * Ritorna gli ordini previsti per un determinato cliente
     * @param cliente per cui si vogliono vedere gli ordini
     * @return ordini previsti per il cliente
     * @throws NessunOrdineException se non vengono trovati ordini per il cliente
     */
    public Collection<Ordine> ordiniPrevistiPerCliente(Cliente cliente, LocalDate data) throws NessunOrdineException;

    /**
     * Calcola il costo dell'ordine sulla base del listino prezzi,
     * la distanza del luogo di consegna ed eventuali sconti
     * @param ordine per cui si vuole calcolare il prezzo
     * @return prezzo dell'ordine in euro
     */
    public int determinaPrezzoOrdine(Ordine ordine);

    /**
     * Ritorna la consegna all'interno del quale è stato inserito l'ordine
     * @param ordine per cui si vuole ottenere la consegna
     * @return consegna a cui appartiene l'ordine
     */
    public Consegna ottieniConsegna(Ordine ordine);

    /**
     * Aggiunge un ordine alla lista degli ordini futuri
     * @param ordine
     */
    public void aggiungiOrdine(Ordine ordine);
}

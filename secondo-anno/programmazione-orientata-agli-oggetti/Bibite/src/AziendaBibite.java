import Eccezioni.NessunOrdineException;
import Enumerazioni.StatoConsegna;
import Interfacce.RegistroConsegne;
import Interfacce.RegistroClienti;
import Interfacce.RegistroOrdini;
import Oggetti.Cliente;
import Oggetti.Consegna;
import Oggetti.Ordine;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Rappresenta l'azienda di bibite con i servizi offerti.
 * Offre operazioni per:
 * Ottenere gli ordini futuri di un determinato cliente,
 * Determinare il costo di un ordine,
 * Ottenere le consegne previste per oggi,
 * Le consegne partite/incorso con la relativa ora di partenza,
 * Le consegne terminate/fallite con la relativa ora di chiusura.
 */
public class AziendaBibite {
    RegistroConsegne consegne;
    RegistroOrdini ordini;
    RegistroClienti clienti;

    AziendaBibite(){}

    /**
     * Permette di ottenere gli ordini prenotati da un certo cliente
     * @param cliente per cui si vogliono ottenere gli ordini
     * @return tutti gli ordini futuri del cliente
     * @throws NessunOrdineException se non vengono trovati ordini
     */
    public Collection<Ordine> ordiniFuturiPerCliente(Cliente cliente) throws NessunOrdineException {
        return ordini.ordiniPrevistiPerCliente(cliente, LocalDate.now());
    }

    /**
     * Determina il costo di un ordine
     * @param ordine ordine per cui si vuole determinare il costo
     * @return prezzo dell'ordine in euro
     */
    public int determinaCostoOrdine(Ordine ordine){
        return ordini.determinaPrezzoOrdine(ordine);
    }

    /**
     * Permette di ottenere le consegne previste per oggi
     * @return consegne previste per oggi
     */
    public Collection<Consegna> ottieniConsegnePerOggi(){
        return consegne.ottieniConsegneStato(StatoConsegna.OGGI);
    }

    /**
     * Permette di ottenere le consegne in corso
     * @return consegne in corso
     */
    public Collection<Consegna> ottieniConsegneInCorso(){
        return consegne.ottieniConsegneStato(StatoConsegna.IN_CORSO);
    }

    /**
     * Permette di ottenere le consegne terminate
     * @return consegne terminate
     */
    public Collection<Consegna> ottieniConsegneTerminate(){
        return consegne.ottieniConsegneStato(StatoConsegna.TERMINATA);
    }
}

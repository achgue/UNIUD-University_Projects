import Eccezioni.OrdineGiaAssociatoException;
import Eccezioni.TavoloGiaEsistenteException;
import Eccezioni.TavoloSenzaOrdiniException;
import Enumerazioni.StatoPiatto;
import Registri.RegistroOrdiniImpl;
import Registri.RegistroPiattiImpl;
import Registri.RegistroTavoliImpl;
import Interfacce.RegistroOrdini;
import Interfacce.RegistroPiatti;
import Interfacce.RegistroTavoli;
import Oggetti.Ordine;
import Oggetti.PiattiTavolo;
import Oggetti.Piatto.Piatto;
import Oggetti.Tavolo;

import java.util.Collection;

/**
 * Rappresenta la pizzeria con i servizi offerti.
 * Permette di effettuare operazioni di:
 * Creare una comanda,
 * Estrarre i piatti non ancora consegnati,
 * Calcolare il conto di un tavolo
 */
public class Pizzeria {

    private RegistroPiatti registroPiatti;
    private RegistroTavoli registroTavoli;
    private RegistroOrdini registroOrdini;

    Pizzeria(){
        this.registroPiatti = new RegistroPiattiImpl();
        this.registroTavoli = new RegistroTavoliImpl();
        this.registroOrdini = new RegistroOrdiniImpl();
    }

    /**
     * Crea una comanda dato un ordine e il tavolo a cui è associato
     * @param piattiTavolo pietanze ordinate da un tavolo
     * @param tavolo tavolo che effettua la comanda
     * @return la comanda riferita al tavolo
     * @throws OrdineGiaAssociatoException se lo stesso ordine è già stato fatto
     */
    public Ordine creaOrdineEAssocia(PiattiTavolo piattiTavolo, Tavolo tavolo) throws OrdineGiaAssociatoException {
        Ordine ordine = registroOrdini.creaOrdine(piattiTavolo);
        registroTavoli.associaOrdineTavolo(tavolo, ordine);
        registroPiatti.aggiungiPiatti(piattiTavolo);
        return ordine;
    }

    /**
     * Estrae i piatti che non ancora consegnati al rispettivo tavolo
     * @return i piatti che non ancora consegnati
     */
    public Collection<Piatto> piattiDaConsegnare(){
        return registroPiatti.estraiPiattiConStato(StatoPiatto.DA_CONSEGNARE);
    }

    /**
     * Calcola il conto per un determinato tavolo
     * @param tavolo tavolo per cui si vuole calcolare il conto
     * @return prezzo del conto in euro
     * @throws TavoloSenzaOrdiniException se il tavolo non ha ordini associati
     */
    public int calcolaConto(Tavolo tavolo) throws TavoloSenzaOrdiniException {
        return registroTavoli.calcolaConto(tavolo);
    }

    /**
     * Aggiunge un tavolo al registro dei tavoli
     * @param tavolo
     * @throws TavoloGiaEsistenteException se il tavolo è stato già inserito nel registro
     */
    public void aggiungiTavolo(Tavolo tavolo) throws TavoloGiaEsistenteException {
        registroTavoli.aggiungiTavolo(tavolo);
    }

}

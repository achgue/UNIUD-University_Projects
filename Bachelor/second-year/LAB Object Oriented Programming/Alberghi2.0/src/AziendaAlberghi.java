import Eccezioni.AlberghiNonTrovatiException;
import Interfacce.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Rappresenta l'azienda di alberghi con i suoi clienti
 * gli alberghi e il listino prezzi.
 * Permette di effettuare operazioni di:
 * Ricerca di un albergo,
 * Calcolo del prezzo,
 * Modifica del listino prezzi,
 * Calcolo degli sconti.
 */
public class AziendaAlberghi {

    Alberghi alberghi;
    Clienti clienti;
    ListinoPrezzi listinoPrezzi;
    Prenotazioni prenotazioni;

    AziendaAlberghi(Alberghi alberghi, Clienti clienti, ListinoPrezzi listinoPrezzi, Prenotazioni prenotazioni){
        this.alberghi = alberghi;
        this.clienti = clienti;
        this.listinoPrezzi = listinoPrezzi;
        this.prenotazioni = prenotazioni;
    }

    /**
     * Cerca nella lista degli alberghi tutti quelli disponibili
     * in un determinato periodo con le caratteristiche date
     * @param caratteristicheAlbergo caratteristiche dell'albergo
     * @param caratteristicheStanza caratteristiche della stanza cercata
     * @param periodo periodo per il quale si cerca la disponibilità
     * @return Elenco di alberghi che soddisfano le caratteristiche
     * date e disponibili nel dato periodo
     * @throws AlberghiNonTrovatiException se non vengono trovati alberghi
     */
    public Collection<Stanza> cercaAlberghi(Caratteristiche caratteristicheAlbergo, Caratteristiche caratteristicheStanza, Periodo periodo)
            throws AlberghiNonTrovatiException {
        Collection<Stanza> stanzeCheSoddisfano = new ArrayList<Stanza>();
        for(Albergo albergo : alberghi.getAlberghi()){
            if(caratteristicheAlbergo.soddisfaCaratteristiche(albergo)){
               for(Stanza stanza : alberghi.getStanze(albergo)){
                   if (caratteristicheStanza.soddisfaCaratteristiche(stanza)){
                       prenotazioni.disponibile(stanza, periodo);
                       stanzeCheSoddisfano.add(stanza);
                   }
               }
            }
        }
        return stanzeCheSoddisfano;
    }

    /**
     * Calcola il prezzo previsto per una determinata prenotazione
     * @param prenotazione per cui si vuole calcolare il prezzo
     * @return il prezzo in euro previsto per la prenotazione
     */
    public int calcolaPrezzoPrenotazione(Prenotazione prenotazione){
        return prenotazione.calcolaPrezzoPrevisto();
    }

    /**
     * Data una stanza modifica il suo prezzo sul listino prezzi
     * @param stanza stanza di cui si vuole modificare il prezzo
     * @param prezzoAPersona prezzo a persona in euro
     */
    public void modificaListinoPrezzi(Stanza stanza, int prezzoAPersona){
        listinoPrezzi.modifica(stanza, prezzoAPersona);
    }

    /**
     * Calcola lo sconto per un cliente su una sua prenotazione
     * @param prenotazione prenotazione effettuata dal cliente
     * @return sconto applicabile sul prezzo previsto dalla prenotazione
     */
    public int calcolaSconti(Prenotazione prenotazione){
        return prenotazioni.calcolaPrezzoPrevisto(prenotazione);
    }

}

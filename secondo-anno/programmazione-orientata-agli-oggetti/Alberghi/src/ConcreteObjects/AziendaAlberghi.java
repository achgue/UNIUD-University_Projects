package ConcreteObjects;

import eccezioni.AlberghiNonTrovatiException;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Rappresenta l'azienda di alberghi.
 * Permette di effettuare operazioni di:
 * Ricerca di un albergo,
 * Calcolo del prezzo,
 * Modifica del listino prezzi,
 * Applicazione di sconti
 * Gestisce gli alberghi e i rapporti con i clienti.
 * Gestisce prenotazioni passate, in cosrso e future
 */
public class AziendaAlberghi {

    List<Cliente> clienti;
    List<Albergo> alberghi;

    List<Prenotazione> prenotazionePassate;
    List<Prenotazione> prenotazioneInCorso;
    List<Prenotazione> prenotazioneFuture;

    ListinoPrezzi listinoPrezzi;

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
    public Collection<Stanza> cercaAlberghi(FiltroCaratteristiche caratteristicheAlbergo, FiltroCaratteristiche caratteristicheStanza, Periodo periodo) throws AlberghiNonTrovatiException {
        Collection<Stanza> stanzeCheSoddisfano = new ArrayList<Stanza>();
        Collection<Albergo> alberghiCheSoddisfano = FiltroGenerico.filtra(alberghi, caratteristicheAlbergo);
        Collection<Stanza> risultato = new ArrayList<Stanza>();
        for (Albergo albergo: alberghiCheSoddisfano){
            stanzeCheSoddisfano = FiltroGenerico.filtra(albergo.getStanze(), caratteristicheStanza);
            risultato = (Collection<Stanza>) Stream.concat(risultato.stream(), stanzeCheSoddisfano.stream());
        }

        if(stanzeCheSoddisfano.size() == 0){
            throw new AlberghiNonTrovatiException();
        }else{
            return stanzeCheSoddisfano;
        }
    }


    /**
     * Calcola il prezzo previsto per una determinata prenotazione
     * @param prenotazione per cui si vuole calcolare il prezzo
     * @return il prezzo in euro previsto per la prenotazione
     */
    public int calcolaPrezzoPrenotazione(@NonNull Prenotazione prenotazione){
        return prenotazione.getPrezzoPrevisto();
    }

    /**
     * Data una stanza modifica il suo prezzo sul listino prezzi
     * @param stanza stanza di cui si vuole modificare il prezzo
     * @param prezzoAPersona prezzo a persona in euro
     */
    public void modificaListinoPrezzi(Stanza stanza, int prezzoAPersona){
        stanza.setPrezzoPerPersona(prezzoAPersona);
    }

    /**
     * Calcola lo sconto per un cliente su una sua prenotazione
     * @param prenotazione prenotazione effettuata dal cliente
     * @return sconto applicabile sul prezzo previsto dalla prenotazione
     */
    public int calcolaSconti(Prenotazione prenotazione){
        if(prenotazione.getCliente().getDataInizioRapporti().isBefore(LocalDate.now())){
            return 10;
        }else{
            return 0;
        }
    }

    /**
     * Aggiunge cliente alla lista dei clienti
     * @param cliente
     */
    public void aggiungiCliente(Cliente cliente) {
        clienti.add(cliente);
    }

    /**
     * Aggiunge un albergo alla lista degli alberghi
     * @param albergho
     */
    public void aggiungiAlbergo(Albergo albergho) {
        alberghi.add(albergho);
    }

    /**
     * Cerca un albergo nella lista degli alberghi
     * @param albergho albergo da cercare
     * @return ritorna albergo cercato nella lista
     */
    public Albergo trovaAlbergo(Albergo albergho) {
        return null;
    }
}

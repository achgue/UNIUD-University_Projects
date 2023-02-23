import eccezioni.CambioDiStatoIllegaleException;
import eccezioni.NessunaBarcaTrovataException;
import eccezioni.BarcaNonDisponibileException;
import enumerazioni.StatoBarca;
import enumerazioni.StatoNoleggio;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rappresenta l'azienda di noleggio barche.
 * Permette di effettuare:
 * operazioni di ricerca di una barca,
 * noleggio di una barca,
 * pagamento e ritiro di un mezzo noleggiato,
 * consegna del mezzo.
 */
public class AziendaNoleggioBarche {
    private List<Barca> flottaBarche;
    private List<Cliente> clienti;

    private List<Noleggio> noleggiPassati;
    private List<Noleggio> noleggiInCorso;
    private List<Noleggio> noleggiFuturi;


    AziendaNoleggioBarche(List<Barca> flottaBarche){
        this.flottaBarche = flottaBarche;
    }

    /**
     * Cerca una barca con determinate caratteristiche tra le barche dell'azienda che sono disponibili
     * @param caratteristiche rappresenta le caratteristiche che la barca deve soddisfare
     * @return elenco di barche disponibili con le date caratteristiche
     * @throws NessunaBarcaTrovataException se nessuna barca è disponibile o ha le caratteristiche specificate
     */
    List<Barca> cercaBarca(Caratteristica caratteristiche) throws NessunaBarcaTrovataException {
        List<Barca> risultato = flottaBarche.stream().filter(barca -> caratteristiche.equals(barca.caratteristiche)).collect(Collectors.toList());
        if(risultato.size() == 0){
            throw new NessunaBarcaTrovataException();
        }else{
            return risultato;
        }
    }

    /**
     * Aggiunge una barca alla flotta
     * @param barca
     */
    public void aggiungiBarcaAllaFlotta(Barca barca){
        flottaBarche.add(barca);
    }

    /**
     * Verifica se la barca è disponibile e in caso positivo crea un noleggio
     * Il noleggio viene aggiunto alla lista dei noleggi futuri
     * @param barca barca per cui si vuole effettuare il noleggio   |
     * @param cliente cliente che effettua il noleggio della barca  |--> possono essere convertiti in codici e utilizzare dei metodi di ricerca che restituiscano l'oggetto
     * @param luogoRitiro luogo in cui il cliente ritirerà il mezzo
     * @param luogoConsegna luogo accordato per la consegna del mezzo a fine noleggio
     * @param dataInizio data inizio del noleggio
     * @param dataFine data fine del noleggio
     * @return ritorna il noleggi con il suo costo
     * @throws BarcaNonDisponibileException se la barca non è disponibile nelle date concordate
     * @throws CambioDiStatoIllegaleException se il passaggio di stato della consegna o della prenotazione non sono ammissibili
     */
    Noleggio noleggioBarca(Barca barca, Cliente cliente, Posizione luogoRitiro, Posizione luogoConsegna, Date dataInizio, Date dataFine) throws BarcaNonDisponibileException, CambioDiStatoIllegaleException {
        if(barca.èDisponibile(dataInizio, dataFine)){
            Noleggio noleggio = new Noleggio(barca.getCodiceUnivoco(),
                                        cliente.getCodiceUnivoco(),
                                        luogoRitiro,
                                        luogoConsegna,
                                        dataInizio,
                                        dataFine);
            barca.setStato(StatoBarca.PRENOTATO);
            noleggio.cambiaStato(StatoNoleggio.PRENOTATO);
            noleggio.calcoloCostoNoleggio(barca);
            noleggiFuturi.add(noleggio);
            return noleggio;
        }else{
            throw new BarcaNonDisponibileException();
        }
    }

    /**
     * Consegna la barca al cliente e segna il pagamento
     * Il noleggio è aggiunto alla lista dei noleggi in corso
     * @param barca barca da consegnare
     * @param noleggio noleggio appena effettuato
     * @return prezzo da pagare per il noleggio in euro
     * @throws CambioDiStatoIllegaleException se il passaggio di stato della consegna o della prenotazione non sono ammissibili
     */
    public int ritiraEPaga(Barca barca, Noleggio noleggio) throws CambioDiStatoIllegaleException {
        barca.setStato(StatoBarca.CONSEGNATO);
        noleggio.cambiaStato(StatoNoleggio.IN_CORSO);
        noleggiFuturi.remove(noleggio);
        noleggiInCorso.add(noleggio);
        return noleggio.calcoloCostoNoleggio(barca);
    }

    /**
     * Il cliente consegna la barca e paga l'eventuale penalità se necessario
     * Il noleggio è aggiunto alla lista dei noleggi chiusi
     * @param barca barca da consegnare
     * @param noleggio noleggio appena effettuato
     * @return prezzo da pagare per il penalità in euro
     * @throws CambioDiStatoIllegaleException se il passaggio di stato della consegna o della prenotazione non sono ammissibili
     */
    public int consegnaBarca(Barca barca, Noleggio noleggio) throws CambioDiStatoIllegaleException {
        //int costoPenalità = noleggio.calcolaPenalità(barca);
        barca.setStato(StatoBarca.DISPONIBILE);
        noleggio.cambiaStato(StatoNoleggio.CHIUSO);
        noleggiInCorso.remove(noleggio);
        noleggiPassati.add(noleggio);
        return noleggio.calcoloCostoPenalità(barca);
    }

    public void registraClienti(Cliente cliente) {
        clienti.add(cliente);
    }
}

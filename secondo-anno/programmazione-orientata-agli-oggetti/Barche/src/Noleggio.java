import eccezioni.CambioDiStatoIllegaleException;
import enumerazioni.StatoBarca;
import enumerazioni.StatoNoleggio;
import serviziExtra.Extra;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Rappresenta un noleggio con tutte le sue informazioni
 * Ha uno stato che si evolve nel tempo
 * Una lista di servizi extra
 * Permette di calcolare il costo base di un noleggio
 * (con eventuali sconti) e la penalità se necessaria
 */
public class Noleggio {
    private final String codiceUnivocoBarca;
    private final String codiceUnivocoCliente;
    private final Posizione luogoRitiro;
    private final Posizione luogoConsegna;
    private final Date dataInizio;
    private final Date dataFine;
    private List<Extra> serviziExtra;
    private StatoNoleggio statoNoleggio;

    public Noleggio(String codiceUnivocoBarca, String codiceUnivocoCliente, Posizione luogoRitiro, Posizione luogoConsegna, Date dataInizio, Date dataFine) {
        this.codiceUnivocoBarca = codiceUnivocoBarca;
        this.codiceUnivocoCliente = codiceUnivocoCliente;
        this.luogoRitiro = luogoRitiro;
        this.luogoConsegna = luogoConsegna;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public void addServizioExtra(Extra extra){
        serviziExtra.add(extra);
    }

    /**
     * Calcola il costo base del noleggio considerando servizi extra e costo della barca
     * @param barca da noleggiare
     * @return costo base del noleggio
     */
    public int calcoloCostoNoleggio(Barca barca){
        Iterator<Extra> extraIterator = serviziExtra.listIterator();
        int costoSeriziExtra = 0;
        int durataViaggio = 0;
        while (extraIterator.hasNext()){
            costoSeriziExtra += extraIterator.next().getCosto();
        }
        return barca.getCostoGiornalieroNoleggio() + costoSeriziExtra;
    }

    /**
     * Modifica lo stato del noleggio
     * @param statoNoleggio
     */
    public void cambiaStato(StatoNoleggio statoNoleggio) throws CambioDiStatoIllegaleException {
        if(statoNoleggio.transazioneAmmissibile(this.statoNoleggio, statoNoleggio)){
            this.statoNoleggio = statoNoleggio;
        }else{
            throw new CambioDiStatoIllegaleException();
        }
    }

    /**
     * Ritorna il costo della penalità se la barca va in manutenzione
     * @param barca restituita dal cliente
     * @return costo della penalità
     */
    public int calcoloCostoPenalità(Barca barca) {
        int penalità = 100;
        if(barca.getStatoBarca() == StatoBarca.IN_MANUTENZIONE){
            return penalità;
        }else{
            return 0;
        }
    }
}

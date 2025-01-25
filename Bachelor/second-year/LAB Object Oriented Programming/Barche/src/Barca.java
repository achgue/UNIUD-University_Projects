import eccezioni.CambioDiStatoIllegaleException;
import enumerazioni.Marca;
import enumerazioni.StatoBarca;

import java.util.Date;

/**
 * Rappresenta una barca con delle sue caratteristiche
 * e un suo stato
 * Permette di verificare la disponibilità di una barca
 * e modificare il suo stato nel tempo
 */
public class Barca implements OggettiUnivoci<String>{
    private final String targa;
    private Marca marca;

    private int costoGiornalieroNoleggio;
    private StatoBarca statoBarca;
    public Caratteristica caratteristiche;


    public Barca(String targa, int costoGiornalieroNoleggio) {
        this.targa = targa;
        this.costoGiornalieroNoleggio = costoGiornalieroNoleggio;
        statoBarca = StatoBarca.DISPONIBILE;
    }
    /**
     * Ritorna il codice univoco dell'oggetto
     * @return codice univoco della barca
     */
    public String getCodiceUnivoco() {
        return targa;
    }

    public int getCostoGiornalieroNoleggio() {
        return costoGiornalieroNoleggio;
    }

    /**
     * Modifica lo stato della barca
     * @param statoBarca nuovo stato
     * @throws CambioDiStatoIllegaleException se il cambio di stato non è ammissibile
     */
    public void setStato(StatoBarca statoBarca) throws CambioDiStatoIllegaleException {
        if(statoBarca.transazioneAmmissibile(this.statoBarca, statoBarca)){
            this.statoBarca = statoBarca;
        }else{
            throw new CambioDiStatoIllegaleException();
        }
    }

    public StatoBarca getStatoBarca() {
        return statoBarca;
    }

    /**
     * restituisce true se la barca è libera nel periodo indicato
     * @param dataInizio
     * @param dataFine
     * @return
     */
    public boolean èDisponibile(Date dataInizio, Date dataFine) {
        return true;
    }
}

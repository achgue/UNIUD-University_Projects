import java.time.LocalDate;


/**
 * Oggetto immutabile per rappresentare i clienti
 * che hanno avuto rapporti con l'azienda
 */
public class Cliente implements OggettiUnivoci<String>{
    private final String codiceFiscale;
    private final String nome;
    private final String cognome;
    private final LocalDate dataInizioRapporti;


    public Cliente(String codiceFiscale, String nome, String cognome, LocalDate dataInizioRapporti) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.dataInizioRapporti = dataInizioRapporti;
    }

    public LocalDate getDataInizioRapporti() {
        return dataInizioRapporti;
    }

    /**
     * Ritorna il codice univoco dell'oggetto
     * @return codice fiscale del cliente
     */
    @Override
    public String getCodiceUnivoco() {
        return codiceFiscale;
    }
}

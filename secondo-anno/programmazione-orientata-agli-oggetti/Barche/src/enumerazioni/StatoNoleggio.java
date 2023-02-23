package enumerazioni;

public enum StatoNoleggio {
    PRENOTATO(1),
    IN_CORSO(2),
    CHIUSO(3);


    private int ordine;
    StatoNoleggio(int ordine) {
        this.ordine = ordine;
    }

    public boolean transazioneAmmissibile(StatoNoleggio statoNoleggio1, StatoNoleggio statoNoleggio2){
        return statoNoleggio1.ordine <= statoNoleggio2.ordine;
    }
}

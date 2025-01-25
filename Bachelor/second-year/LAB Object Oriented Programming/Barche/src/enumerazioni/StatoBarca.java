package enumerazioni;

public enum StatoBarca {
    DISPONIBILE(1), IN_MANUTENZIONE(1),
    PRENOTATO(2), 
    CONSEGNATO(3);

    private int ordine;
    StatoBarca(int ordine) {
        this.ordine = ordine;
    }

    public boolean transazioneAmmissibile(StatoBarca statoBarca1, StatoBarca statoBarca2){
        return statoBarca1.ordine <= statoBarca2.ordine;
    }
}

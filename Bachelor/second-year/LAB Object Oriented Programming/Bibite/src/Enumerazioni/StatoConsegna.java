package Enumerazioni;

public enum StatoConsegna {
    OGGI(0),
    IN_CORSO(1),
    TERMINATA(2), FALLITA(2);

    private int stato;
    StatoConsegna(int stato) {
        this.stato = stato;
    }
}

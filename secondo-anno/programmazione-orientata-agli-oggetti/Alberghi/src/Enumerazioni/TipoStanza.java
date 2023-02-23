package Enumerazioni;

public enum
TipoStanza {
    APPARTAMENTO(1),
    SINGOLA(2),
    DOPPIA(3),
    TRIPLA(4);

    int ordine;
    TipoStanza(int ordine) {
        this.ordine = ordine;
    }
}

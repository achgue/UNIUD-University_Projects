import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Definisce le caratteristiche di una barca e da queste
 * permette di calcolare un prezzo di giornaliero per il noleggio
 * valutando anche eventuali extra
 */
public class Caratteristica {
    private final int numeroAlberi;
    private final int numeroPosti;
    private final int dimensione;
    @Setter @Getter
    private boolean conCondizionatore;
    @Setter @Getter
    private boolean conTelevisione;
    @Setter @Getter
    private boolean conTendalini;
    private int etàDellaBarca;
    private String Modello;

    public Caratteristica(int numeroAlberi, int numeroPosti, int dimensione) {
        this.numeroAlberi = numeroAlberi;
        this.numeroPosti = numeroPosti;
        this.dimensione = dimensione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caratteristica that = (Caratteristica) o;
        return numeroAlberi == that.numeroAlberi
                && numeroPosti == that.numeroPosti
                && dimensione == that.dimensione
                && conCondizionatore == that.conCondizionatore
                && conTelevisione == that.conTelevisione
                && conTendalini == that.conTendalini
                && Objects.equals(Modello, that.Modello);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroAlberi, numeroPosti, dimensione, conCondizionatore, conTelevisione, conTendalini, etàDellaBarca, Modello);
    }
}

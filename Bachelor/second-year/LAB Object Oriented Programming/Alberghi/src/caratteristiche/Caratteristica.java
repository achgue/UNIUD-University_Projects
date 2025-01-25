package caratteristiche;

public interface Caratteristica<T> {

    /**
     * Valuta se una caratteristica è soddisfatta da un oggetto t
     * @param t
     * @return true se è soddisfatta, false altrimenti
     */
    public boolean èSoddisfatta(T t);
}

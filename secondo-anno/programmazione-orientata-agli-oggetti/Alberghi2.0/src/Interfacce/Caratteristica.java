package Interfacce;

public interface Caratteristica <T>{

    /**
     * Verifica che una caratteristica sia soddisfatta
     * @param t oggetto per cui si vuole effettuare la verifica
     * @return true se è soddisfatta, false altrimenti
     */
    public boolean soddisfatta(T t);
}

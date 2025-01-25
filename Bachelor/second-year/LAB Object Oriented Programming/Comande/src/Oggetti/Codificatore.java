package Oggetti;

/**
 * Permette di generare un codice univoco per gli oggetti che lo richiedono
 */
public class Codificatore {

    static int id = 0;

    public int generaId() {
        return id++;
    }
}

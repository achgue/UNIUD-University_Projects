package Oggetti;

/**
 * Oggetto immutabile che rappresenta il comune
 */
public class Comune {
    private final String nome;
    private final String cap;

    public Comune(String nome, String cap) {
        this.nome = nome;
        this.cap = cap;
    }

    /**
     *
     * @return il costo della spedizione
     */
    public int prezzoSpedizione() {
        return 0;
    }
}

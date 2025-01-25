package Oggetti;

import java.util.Collection;

/**
 * Oggetto immutabile che rappresenta una consegna
 */
public class Tragitto {
    private final Collection<Comune> comuni;

    public Tragitto(Collection<Comune> comuni) {
        this.comuni = comuni;
    }
}

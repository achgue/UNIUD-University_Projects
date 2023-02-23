package Oggetti;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * Oggetto che rappresenta univocamente un tavolo con ordini associati
 */
public class Tavolo {

    private Codificatore codificatoreTavolo;

    @Getter
    private final int id;
    @Getter @Setter
    private int numeroPersone;
    @Getter
    private Collection<Ordine> ordini;

    public Tavolo(int numeroPersone) {
        this.id = codificatoreTavolo.generaId();
        this.numeroPersone = numeroPersone;
    }

    public void aggiungiOrdine(Ordine ordine){
        ordini.add(ordine);
    }
}

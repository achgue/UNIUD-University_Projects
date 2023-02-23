package ConcreteObjects;

import caratteristiche.Caratteristica;
import lombok.Getter;

import java.util.List;

/**
 *  Rappresenta un albergo posseduto dall'azienda.
 *  Permette di:
 *  Aggiungere stanze,
 *  Modificare le stelle dell'albergo,
 *  Aggiungere stanze,
 *  Aggiungere caratteristiche.
 */
public class Albergo {
    @Getter
    private final String codiceAlbergo;
    @Getter
    private Stelle stelle;
    @Getter
    private final Località località;
    @Getter
    private FiltroCaratteristiche<Albergo> caratteristiche;
    @Getter
    private List<Stanza> stanze;

    public Albergo(String codiceAlbergo, Stelle stelle, Località località) {
        this.codiceAlbergo = codiceAlbergo;
        this.stelle = stelle;
        this.località = località;
    }

    /**
     * Aggiunge una stanza all'albergo
     * @param stanza
     */
    public void aggiungiStanza(Stanza stanza) {
        stanze.add(stanza);
    }

    /**
     * Aggiunge una caratteristica all'albergo
     * @param caratteristica
     */
    public void aggiungiCaratteristiche(Caratteristica caratteristica){
        caratteristiche.aggiungiCaratteristica(caratteristica);
    }

}

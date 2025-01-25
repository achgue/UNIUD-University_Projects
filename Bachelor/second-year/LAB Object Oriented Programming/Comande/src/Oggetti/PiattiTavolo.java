package Oggetti;

import Oggetti.Piatto.Piatto;
import lombok.Getter;

import java.util.Collection;

/**
 * Rappresenta i piatti ordinati da un tavolo
 */
public class PiattiTavolo {
    @Getter
    private Collection<Piatto> piatti;

    public PiattiTavolo(Collection<Piatto> piatti) {
    }


    /**
     * Calcola il prezzo dei piatti ordinati
     * @return prezzo dell'ordine
     */
    public int ottieniPrezzoPiatti(){
        int prezzoOrdine = 0;
        for(Piatto piatto: piatti){
            prezzoOrdine += piatto.getPrezzo();
        }
        return prezzoOrdine;
    }

}

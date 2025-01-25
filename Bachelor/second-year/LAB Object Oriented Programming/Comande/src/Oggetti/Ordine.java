package Oggetti;

import Oggetti.Piatto.Piatto;

import java.time.LocalTime;

public class Ordine {

    private Codificatore codificatore;

    private final int id;
    private final PiattiTavolo piattiTavolo;
    private final LocalTime oraOrdine;

    public Ordine(PiattiTavolo piattiTavolo) {
        this.id = codificatore.generaId();
        this.piattiTavolo = piattiTavolo;
        this.oraOrdine = LocalTime.now();
    }

    /**
     * @return prezzo dell'ordine
     */
    public int ottieniPrezzoOrdine() {
        return piattiTavolo.ottieniPrezzoPiatti();
    }
}

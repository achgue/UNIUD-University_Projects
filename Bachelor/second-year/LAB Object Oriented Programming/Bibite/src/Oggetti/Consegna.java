package Oggetti;

import Enumerazioni.StatoConsegna;
import Enumerazioni.TipoConsegna;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Rappresenta una consegna con i sui ordini, il tragitto che deve effettuare,
 * ora di partenza/terminazione, stato e tipo.
 * L'oggetto muta nel tempo.
 */
public class Consegna {
    private int id;
    private Collection<Ordine> ordini;
    @Setter
    private Tragitto tragitto;
    @Setter
    private LocalDateTime dataOraPartenza;
    @Setter
    private LocalDateTime dataOraTerminazione;
    @Setter
    private StatoConsegna statoConsegna;
    private final TipoConsegna tipoConsegna;

    Consegna(int id, Collection<Ordine> ordini, Tragitto tragitto, TipoConsegna tipoConsegna){
        this.id = id;
        this.ordini = ordini;
        this.tragitto = tragitto;
        this.tipoConsegna = tipoConsegna;
    }

    public void aggiungiOrdine(Ordine ordine){
        ordini.add(ordine);
    }

}

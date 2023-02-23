package Oggetti;

import lombok.Getter;
import lombok.NonNull;

import java.util.Collection;

/**
 * Rappresenta l'ordine effettuato da un determinato cliente con la merce acquistata
 */
public class Ordine {
    private final int id;
    @Getter
    private final Cliente cliente;
    private Consegna consegna;
    @Getter
    private Collection<Merce> merci;
    @Getter
    private final Comune luogoConsegna;

    public Ordine(int id, Cliente cliente, Collection<Merce> merci,@NonNull Comune luogoConsegna) {
        this.id = id;
        this.cliente = cliente;
        this.merci = merci;
        this.luogoConsegna = luogoConsegna;
    }

    /**
     * aggiunge merce all'ordine
     * @param merce
     */
    public void aggiungiMerce(Merce merce){
        merci.add(merce);
    }

    /**
     * Modifica la consegna a cui è assegnato l'ordine
     * @param consegna
     */
    public void modificaConsegna(Consegna consegna){
        this.consegna = consegna;
    }

    public Cliente ottieniCliente() {
        return cliente;
    }

    /**
     * Calcola il prezzo dell'ordine
     * @return
     */
    public int calcolaPrezzoMerce() {
        return 0;
    }
}

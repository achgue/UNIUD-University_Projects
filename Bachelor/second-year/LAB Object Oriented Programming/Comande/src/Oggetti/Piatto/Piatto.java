package Oggetti.Piatto;

import Enumerazioni.StatoPiatto;
import Enumerazioni.TipoPiatto;
import Oggetti.Codificatore;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * Rappresenta univocamente un piatto/pietanza
 */
public class Piatto {

    private Codificatore codificatorePiatti;

    @Getter
    private Collection<Piatto> varianti;
    private final int id;
    @Setter @Getter
    private StatoPiatto statoPiatto;
    @Getter @Setter
    private String nomePiatto;
    private final TipoPiatto tipoPiatto;
    @Getter @Setter
    private int prezzo;

    protected Piatto(TipoPiatto tipoPiatto) {
        this.id = codificatorePiatti.generaId();
        this.tipoPiatto = tipoPiatto;
    }

    /**
     * Aggiunge una variante per il relativo piatto/pietanza
     * @param variante variante da aggiungere
     */
    public void aggiungiVariante(Piatto variante){
        varianti.add(variante);
    }


}

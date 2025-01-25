package ConcreteObjects;

import lombok.Getter;
import lombok.Setter;

/**
 * Rappresenta una stanza con le sue caratteristiche.
 * Permette di:
 * Aggiornare le informazioni dell'albergo,
 * Aggiornare il tipo di stanza,
 * Aggiornare il tipo di vista,
 * Aggiornare il piano.
 */
public class Stanza {
    @Getter
    @Setter
    private String codiceAlbergo;
    @Getter
    @Setter
    private int prezzoPerPersona;
    @Getter
    @Setter
    private TipoStanza tipoStanza;
    @Getter
    @Setter
    private TipoVista tipoVista;
    @Getter
    @Setter
    private int piano;


    Stanza(String codiceAlbergo, TipoStanza tipoStanza) {
        this.codiceAlbergo = codiceAlbergo;
        this.tipoStanza = tipoStanza;
    }
}

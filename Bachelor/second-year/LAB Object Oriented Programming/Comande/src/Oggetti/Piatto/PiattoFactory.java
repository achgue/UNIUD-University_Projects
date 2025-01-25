package Oggetti.Piatto;

import Enumerazioni.TipoPiatto;

/**
 * Crea un istanza del piatto in base al suo tipo
 */
public class PiattoFactory {
    public static Piatto creaPiatto(TipoPiatto tipoPiatto){
        switch (tipoPiatto){
            case ANTIPASTO:
                return new Antipasto(tipoPiatto);
            case PRIMO:
                return new Primo(tipoPiatto);
            case SECONDO:
                return new Secondo(tipoPiatto);
            case DOLCE:
                return new Dolce(tipoPiatto);
            case BIBITA:
                return new Bibita(tipoPiatto);
        }
        throw new RuntimeException("Errore nella creazione del piatto");
    }
}

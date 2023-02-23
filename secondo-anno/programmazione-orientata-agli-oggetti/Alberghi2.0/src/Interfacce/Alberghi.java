package Interfacce;

import java.util.Collection;

/**
 * Gestire gli alberghi posseduti dall'azienda.
 * Premette di effetturae operaizioni di:
 * aggiunta/rimozione di un albergo,
 */
public interface Alberghi {
    /**
     * @return le stanze di un determinato albergo
     */
    public Collection<Stanza> getStanze(Albergo albergo);

    /**
     * @return gli alberghi appartenenti all'azienda
     */
    public Collection<Albergo> getAlberghi();
}

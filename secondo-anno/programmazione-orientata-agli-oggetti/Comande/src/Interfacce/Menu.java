package Interfacce;

import Oggetti.Piatto.Piatto;

public interface Menu {

    /**
     * Dato un piatto ritorna il suo prezzo
     * @param piatto
     * @return prezzo di un piatto
     */
    public int ottieniPrezzoPiatto(Piatto piatto);
}

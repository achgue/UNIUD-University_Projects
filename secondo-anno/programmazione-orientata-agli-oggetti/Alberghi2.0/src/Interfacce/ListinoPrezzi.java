package Interfacce;

public interface ListinoPrezzi {
    /**
     * Modifica il prezzo base di una determinata stanza
     * @param stanza stanza a cui modificare il prezzo
     * @param prezzoAPersona nuovo prezzo per la stanza
     */
    public void modifica(Stanza stanza, int prezzoAPersona);
}

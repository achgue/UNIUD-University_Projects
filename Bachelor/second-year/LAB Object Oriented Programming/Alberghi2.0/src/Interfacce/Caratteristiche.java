package Interfacce;

public interface Caratteristiche <T>{
    /**
     * Verifica che l'oggetto soddisfi una serie di caratteristiche
     * @param t oggetto per cui si vuole fare la verifica
     * @return true se le soddisfa tutte, false altrimenti
     */
    public boolean soddisfaCaratteristiche(T t);
}

package Interfacce;

import Eccezioni.NonPresenteInListaExeception;
import Eccezioni.PresenteInListaExeception;
import Oggetti.Cliente;

public interface RegistroClienti {
    /**
     * @param cliente
     * @throws PresenteInListaExeception
     */
    public void aggiungiCliente(Cliente cliente) throws PresenteInListaExeception;

    /**
     * @param cliente
     * @throws NonPresenteInListaExeception
     */
    public void rimuoviCliente(Cliente cliente) throws NonPresenteInListaExeception;
}

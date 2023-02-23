package Servizi;

import Eccezioni.NonPresenteInListaExeception;
import Eccezioni.PresenteInListaExeception;
import Interfacce.RegistroClienti;
import Oggetti.Cliente;

import java.util.Collection;

/**
 * Permette di gestire i clienti con cui l'azienda ha rapporti.
 */
public class RegistroClientiImpl implements RegistroClienti {
    private Collection<Cliente> clienti;

    @Override
    public void aggiungiCliente(Cliente cliente) throws PresenteInListaExeception {

    }

    @Override
    public void rimuoviCliente(Cliente cliente) throws NonPresenteInListaExeception {

    }
}

package Servizi;

import Interfacce.Sconti;
import Oggetti.Cliente;
import Oggetti.Ordine;
import lombok.NonNull;

import java.util.Collection;

public class ScontiImpl implements Sconti {
    private Collection<Cliente> clientiFedeli;

    /**
     * Ritorna lo sconto totale
     * @param ordine per cui si vuole ottenere lo sconto
     * @return
     */

    @Override
    public int calcolaSconto(@NonNull Ordine ordine) {
        int scontoTot = 0;
        for(Cliente cliente: clientiFedeli){
            if(ordine.getCliente().equals(cliente)){
                scontoTot += 100;
            }
        }
        return scontoTot;
    }

    @Override
    public Collection<Cliente> ottieniClientiFedeli() {
        return null;
    }

    @Override
    public void aggiungiClienteFedele(Cliente cliente) {

    }
}

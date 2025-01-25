package Registri;

import Interfacce.RegistroOrdini;
import Oggetti.Ordine;
import Oggetti.PiattiTavolo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Gestisce gli ordini del ristorante
 */
public class RegistroOrdiniImpl implements RegistroOrdini {
    Collection<Ordine> ordini;

    public RegistroOrdiniImpl(){
        ordini = new ArrayList<>();
    }

    @Override
    public Ordine creaOrdine(PiattiTavolo piatti) {
        return new Ordine(piatti);
    }

    @Override
    public void modificaStatoOrdine() {

    }
}

package Registri;

import Eccezioni.OrdineGiaAssociatoException;
import Eccezioni.TavoloSenzaOrdiniException;
import Interfacce.RegistroTavoli;
import Oggetti.Ordine;
import Oggetti.Tavolo;

import java.util.Collection;

public class RegistroTavoliImpl implements RegistroTavoli {
    private Collection<Tavolo> tavoli;

    @Override
    public void associaOrdineTavolo(Tavolo tavolo, Ordine ordine) throws OrdineGiaAssociatoException {
        tavolo.aggiungiOrdine(ordine);
    }

    @Override
    public int calcolaConto(Tavolo tavolo) throws TavoloSenzaOrdiniException {
        if(tavolo.getOrdini().size() == 0){
            throw new TavoloSenzaOrdiniException();
        }else{
            return calcolaPrezzoOrdini(tavolo.getOrdini());
        }
    }

    public void aggiungiTavolo(Tavolo tavolo){
        tavoli.add(tavolo);
    }

    /**
     * Calcola il prezzo di una serie di ordini
     * @param ordini ordini di cui si bisogna calcolare il prezzo
     * @return prezzo degli ordini
     */
    private int calcolaPrezzoOrdini(Collection<Ordine> ordini){
        int prezzoFinale = 0;
        for(Ordine ordine: ordini){
            prezzoFinale += ordine.ottieniPrezzoOrdine();
        }
        return prezzoFinale;
    }
}

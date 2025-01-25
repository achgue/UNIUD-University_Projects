package Registri;

import Eccezioni.TransiozioneNonAmmissibileException;
import Enumerazioni.StatoPiatto;
import Interfacce.RegistroPiatti;
import Oggetti.PiattiTavolo;
import Oggetti.Piatto.Piatto;

import java.util.ArrayList;
import java.util.Collection;

public class RegistroPiattiImpl implements RegistroPiatti {
    private Collection<PiattiTavolo> piattiOrdinati;

    @Override
    public Collection<Piatto> estraiPiattiConStato(StatoPiatto statoPiatto) {
        Collection<Piatto> risultato = new ArrayList<>();

        for (PiattiTavolo piattiTavolo : piattiOrdinati){
            for(Piatto piatto : piattiTavolo.getPiatti()){
                if(piatto.getStatoPiatto().equals(statoPiatto)){
                    risultato.add(piatto);
                }
            }
        }
        return risultato;
    }

    @Override
    public void aggiungiPiatti(PiattiTavolo piattiTavolo){
        piattiOrdinati.add(piattiTavolo);
    }

    @Override
    public void modificaStatoPiatto(StatoPiatto statoPiatto) throws TransiozioneNonAmmissibileException {

    }
}

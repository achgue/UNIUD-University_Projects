package Servizi;

import Enumerazioni.StatoConsegna;
import Interfacce.RegistroConsegne;
import Oggetti.Consegna;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Permette di gestire le consegne future, per oggi, in corso e terminate.
 */
public class RegistroConsegneImpl implements RegistroConsegne {
    private Collection<Consegna> consegneFuture;
    private Collection<Consegna> consegneOggi;
    private Collection<Consegna> consegneInCorso;
    private Collection<Consegna> consegneTerminate;

    public RegistroConsegneImpl(Collection<Consegna> consegneFuture, Collection<Consegna> consegneInCorso, Collection<Consegna> consegneTerminate) {
        this.consegneFuture = consegneFuture;
        this.consegneInCorso = consegneInCorso;
        this.consegneTerminate = consegneTerminate;
    }

    @Override
    public Collection<Consegna> ottieniConsegneStato(StatoConsegna stato) {
        Collection<Consegna> risultato = new ArrayList<Consegna>();
        switch (stato){
            case OGGI:
                return consegneOggi;
            case IN_CORSO:
                return consegneInCorso;
            case TERMINATA:
                return consegneTerminate;
            default:
                throw new RuntimeException("Stato consegna invalido.");
        }
    }
    @Override
    public void modificaConsegneStato(Consegna consegna, StatoConsegna stato) {
        Collection<Consegna> risultato = new ArrayList<Consegna>();
        switch (stato){
            case OGGI:
                consegna.setStatoConsegna(stato);
                consegneOggi.add(consegna);
                break;
            case IN_CORSO:
                consegna.setStatoConsegna(stato);
                consegneInCorso.add(consegna);
                break;
            case TERMINATA:
                consegna.setStatoConsegna(stato);
                consegneTerminate.add(consegna);
                break;
        }
    }

    @Override
    public void impostaDataOraPartenza(LocalDateTime dataOra, Consegna consegna) {
        consegna.setDataOraPartenza(dataOra);
    }

    @Override
    public void impostaDataOraTerminazione(LocalDateTime dataOra, Consegna consegna) {
        consegna.setDataOraTerminazione(dataOra);
    }

}

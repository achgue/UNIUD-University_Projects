package Interfacce;

import Enumerazioni.StatoConsegna;
import Oggetti.Consegna;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Rappresenta le consegne dell'azienda.
 * Ogni consegna provvede a concludere più ordini.
 * Permette di effettuare operazioni di:
 *
 */
public interface RegistroConsegne {
    /**
     * Permette di ottenere le consegne che assumono un determinato stato
     * @param stato delle consegne che vogliamo ottenere
     * @return consegne con lo stato indicato
     */
    public Collection<Consegna> ottieniConsegneStato(StatoConsegna stato);

    /**
     *
     * @param consegna
     * @param stato
     */
    public void modificaConsegneStato(Consegna consegna, StatoConsegna stato);

    public void impostaDataOraPartenza(LocalDateTime dataOra, Consegna consegna);

    public void impostaDataOraTerminazione(LocalDateTime dataOra, Consegna consegna);
}

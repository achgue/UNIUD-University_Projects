package Interfacce;

import Eccezioni.TransiozioneNonAmmissibileException;
import Enumerazioni.StatoPiatto;
import Oggetti.PiattiTavolo;
import Oggetti.Piatto.Piatto;

import java.util.Collection;

public interface RegistroPiatti {

    /**
     * Permette di estrarre i piatti aventi un determinato stato in quel momento
     * @param statoPiatto stato del piatto
     * @return piatti con lo stato indicato
     */
    public Collection<Piatto> estraiPiattiConStato(StatoPiatto statoPiatto);

    /**
     *
     * @param piattiTavolo
     */
    void aggiungiPiatti(PiattiTavolo piattiTavolo);

    /**
     * Modifica lo stato di un determinato piatto
     * @param statoPiatto
     * @throws TransiozioneNonAmmissibileException
     */
    public void modificaStatoPiatto(StatoPiatto statoPiatto) throws TransiozioneNonAmmissibileException;

}

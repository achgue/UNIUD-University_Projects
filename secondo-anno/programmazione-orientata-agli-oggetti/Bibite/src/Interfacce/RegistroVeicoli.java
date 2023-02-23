package Interfacce;

import Oggetti.Veicolo;

public interface RegistroVeicoli {

    /**
     * Verifica la disponibilità di un veicolo
     * @param veicolo per cui si vuole verificare la disponibilità
     * @return true se è disponibile, false altrimenti
     */
    public boolean verificaDisponibilità(Veicolo veicolo);
}

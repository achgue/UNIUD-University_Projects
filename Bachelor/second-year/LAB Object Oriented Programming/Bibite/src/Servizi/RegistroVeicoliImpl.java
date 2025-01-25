package Servizi;

import Interfacce.RegistroVeicoli;
import Oggetti.Veicolo;

public class RegistroVeicoliImpl implements RegistroVeicoli {
    @Override
    public boolean verificaDisponibilità(Veicolo veicolo) {
        return false;
    }
}

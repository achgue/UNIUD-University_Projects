package Oggetti;


import Enumerazioni.StatoVeicolo;
import lombok.Getter;
import lombok.Setter;

public class Veicolo {
    private final String targa;
    @Setter @Getter
    private StatoVeicolo statoVeicolo;
    private final int capacità;

    public Veicolo(String targa, StatoVeicolo statoVeicolo, int capacità) {
        this.targa = targa;
        this.statoVeicolo = statoVeicolo;
        this.capacità = capacità;
    }
}

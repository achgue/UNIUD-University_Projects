package ConcreteObjects;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

/**
 * Rappresenta il periodo per il quale si vuole effettuare la prenotazione
 */
public class Periodo {
    @Getter @Setter
    private Date dataInizio;
    @Getter @Setter
    private Date dataFine;

    Periodo(Date dataInizio, Date dataFine){
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

}

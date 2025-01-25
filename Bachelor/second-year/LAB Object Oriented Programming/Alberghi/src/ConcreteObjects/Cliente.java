package ConcreteObjects;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Oggetto immutabile che rappresenta un cliente che ha avuto rapporti con l'azienda
 */
public class Cliente {
    private final String nome;
    @Getter @Setter
    private final String codiceFiscale;
    @Getter
    private final LocalDate dataInizioRapporti;

    public Cliente(String nome, String codiceFiscale, LocalDate dataInizioRapporti) {
        this.nome = nome;
        this.codiceFiscale = codiceFiscale;
        this.dataInizioRapporti = dataInizioRapporti;
    }
}

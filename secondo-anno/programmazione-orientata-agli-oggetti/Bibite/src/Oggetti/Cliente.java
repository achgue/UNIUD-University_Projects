package Oggetti;

import Interfacce.Identificabile;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Oggetto immutabile che rappresenta il cliente
 */
public class Cliente implements Identificabile<String> {
    private final String codiceFiscale;
    private final String nomeCompleto;
    private final LocalDate dataInizioRapporti;

    public Cliente(String codiceFiscale, String nomeCompleto, LocalDate dataInizioRapporti) {
        this.codiceFiscale = codiceFiscale;
        this.nomeCompleto = nomeCompleto;
        this.dataInizioRapporti = dataInizioRapporti;
    }

    @Override
    public String ottieniCodiceUnivoco() {
        return codiceFiscale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(codiceFiscale, cliente.codiceFiscale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codiceFiscale);
    }
}

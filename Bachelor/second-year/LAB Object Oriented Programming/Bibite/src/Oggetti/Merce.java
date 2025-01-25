package Oggetti;

import Enumerazioni.Marca;
import Enumerazioni.TipoProdotto;
import Enumerazioni.UnitaMisura;
import lombok.Getter;

/**
 * Rappresenta un blocco di merce di un certo tipo con la sua quantità
 */
public class Merce {
    @Getter
    private int quantità;
    private final UnitaMisura unitaMisura;
    private final TipoProdotto tipoProdotto;
    private final Marca marca;
    private int pesoInKgPerUnità;
    private int pesoInKgTot;

    public Merce(int quantità, UnitaMisura unitaMisura, TipoProdotto tipoProdotto, Marca marca) {
        this.quantità = quantità;
        this.unitaMisura = unitaMisura;
        this.tipoProdotto = tipoProdotto;
        this.marca = marca;
    }

    public void modificaQuantità(int n){
        quantità = n;
        impostaPeso();
    }

    private void impostaPeso(){
        pesoInKgTot = pesoInKgPerUnità * quantità;
    }
}

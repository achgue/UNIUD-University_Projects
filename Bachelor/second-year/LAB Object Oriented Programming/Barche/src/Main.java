import eccezioni.CambioDiStatoIllegaleException;
import eccezioni.NessunaBarcaTrovataException;
import eccezioni.BarcaNonDisponibileException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NessunaBarcaTrovataException, CambioDiStatoIllegaleException, BarcaNonDisponibileException {
        Barca barchetta = new Barca("AS485DF", 200);

        Barca barcone = new Barca("SD854ER", 100);
        List<Barca> barche = new ArrayList<>();
        barche.add(barchetta);

        AziendaNoleggioBarche MareESole = new AziendaNoleggioBarche(barche);

        MareESole.aggiungiBarcaAllaFlotta(barcone);

        //cerca le barche che hanno 2 alberi e che sono grandi al piu (10,15,20)
        Caratteristica haDueAlberiEGrande10 = new Caratteristica(2, 200, 11);
        List<Barca> barcheConCaratteristica = MareESole.cercaBarca(haDueAlberiEGrande10);


        //prenota barcone per mario e crea un noleggio
        Cliente mario = new Cliente("aafsfsfasfsa", "mario", "luigi", LocalDate.now());
        MareESole.registraClienti(mario);
        Noleggio noleggioDiMario =
                MareESole.noleggioBarca(barcone,mario, new Posizione(20, 50), new Posizione(40,60),
                        new Date(),
                        new Date()
                );

        //da il barcone a mario e calcola il prezzo del noleggio
        int prezzoBaseMario = MareESole.ritiraEPaga(barcone, noleggioDiMario);

        //L'azienda si riprende il barcone da mario
        int prezzoViolazioniMario = MareESole.consegnaBarca(barcone, noleggioDiMario);
    }
}

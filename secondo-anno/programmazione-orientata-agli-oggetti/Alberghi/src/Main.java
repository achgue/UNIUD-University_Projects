import ConcreteObjects.*;
import Enumerazioni.Località;
import Enumerazioni.Stelle;
import Enumerazioni.TipoStanza;
import eccezioni.AlberghiNonTrovatiException;

import java.time.LocalDate;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws AlberghiNonTrovatiException {
        AziendaAlberghi holtin = new AziendaAlberghi();

        //Creo e aggiungo il cliente mario alla lista dei clienti
        Cliente mario = new Cliente("mario", "ASDSDW854e55d", LocalDate.now());
        holtin.aggiungiCliente(mario);


        //Creo l'albergo e lo aggiungo alla lista degli alberghi
        Albergo albergoMare = new Albergo("AL789", Stelle.QUATTRO, Località.MARE);
        holtin.aggiungiAlbergo(albergoMare);

        //Aggiungo una stanza all'albergo creato
        Stanza stanza = new Stanza("AL789", TipoStanza.APPARTAMENTO);
        holtin.trovaAlbergo(albergoMare).aggiungiStanza(stanza);

        //cerco una stanza con determinate caratteristiche
        //Caratteristica caratteristica =
        holtin.cercaAlberghi(new FiltroCaratteristiche(), new FiltroCaratteristiche() , new Periodo(new Date(2020, 3, 15), new Date(2020, 3, 150)));

        //creo una prenotazione per mario e calcolo il suo prezzo e sconto
        Prenotazione prenotazioneMario = new Prenotazione(mario, 5, stanza);
        System.out.println(prenotazioneMario.getCliente().getCodiceFiscale());
        prenotazioneMario.getCliente();

        int prezzoPrenotazioneMario = holtin.calcolaPrezzoPrenotazione(prenotazioneMario);
        int scontoMario = holtin.calcolaSconti(prenotazioneMario);

        //modifico il prezzo della stanza
        holtin.modificaListinoPrezzi(stanza, 100);
    }
}

import Eccezioni.NessunOrdineException;
import Oggetti.Cliente;
import Oggetti.Comune;
import Oggetti.Consegna;
import Oggetti.Ordine;

import java.time.LocalDate;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {

        AziendaBibite pilfa = new AziendaBibite();
        Cliente mario = new Cliente("DFCMRA78Padsfasdf","Mario Rossi", LocalDate.now());

        Comune udine = new Comune("Udine", "33100");

        Ordine ordine1 = new Ordine(1, mario, null, udine);
        Ordine ordine2 = new Ordine(2, mario, null, udine);

        pilfa.ordini.aggiungiOrdine(ordine1);
        pilfa.ordini.aggiungiOrdine(ordine2);

        try{
            Collection<Ordine> ordiniMario = pilfa.ordiniFuturiPerCliente(mario);
        }catch(NessunOrdineException e){
            System.out.println("Errore nell'aggiunta del cliente.");
        }


        int prezzoOrdine1 = pilfa.determinaCostoOrdine(ordine1);

        Collection<Consegna> perOggi = pilfa.ottieniConsegnePerOggi();
        Collection<Consegna> inCorso = pilfa.ottieniConsegneInCorso();
        Collection<Consegna> terminate = pilfa.ottieniConsegneTerminate();

    }
}
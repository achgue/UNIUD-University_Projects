import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@TODO creare un builder
public class Persona {
    private String nomeCompleto;
    private int eta;
    private List<Somministrazione> somministrazione;

    /**
     * Costruttore della persona
     * @param n nome
     * @param e
     */
    Persona(String n, int e){
        nomeCompleto = n;
        eta = e;
        somministrazione = new ArrayList<Somministrazione>();
    }

    public void setNomeCompleto(String n){
        nomeCompleto = n;
    }
    public String getNomeCompleto(){
        return nomeCompleto;
    }

    public void setEta(int e){
        eta = e;
    }
    public int getEta(){
        return eta;
    }

    public List<Somministrazione> getSomministrazione(){
        return somministrazione;
    }

    /**
     * Riceve un'assunzione, verifica che sia compatibile e la aggiunge alla lista
     * Se il farmaco nell'assunzione è compatibile con la persona, ovvero rispetta tutti i requisiti definiti dalle regole
     * viene aggiunta al registro delle assunzioni
     * altrimenti viene sollevata un eccezione: NotCompatibleException()
     * @param a
     */
    public void aggiungiAssunzione(Somministrazione a){
        if (a.farmaco.isCompatibile(a.farmaco)){
            somministrazione.add(a);
            RegistroSomministrazioni.elencoAssunzioni.add(this);
        }else{
           // NotCompatibleException();
        }
    }

    public String somministrazioneToString() {
        Iterator<Somministrazione> it = somministrazione.iterator();
        while(it.hasNext()) {
            return it.next().data + it.next().farmaco.nome;
        }
        return "";
    }
}

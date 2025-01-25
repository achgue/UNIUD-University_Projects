import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RegistroSomministrazioni implements Iterator<Persona> {
    static ArrayList<Persona> elencoAssunzioni = new ArrayList<Persona>();
    /**
     *Stampa le informazioni di ogni elemento appartenenti all'elenco delle assunzioni
     * @todo Se l'elenco è vuoto solleva eccezione: EmptyRegisterException
     */
    private void stampaRegistroAssunzioni(){
        if(elencoAssunzioni.size()==0)
            System.out.println("Il registro delle assunzioni è vuoto");

        Iterator<Persona> it = elencoAssunzioni.iterator();
        while(it.hasNext()) {
            System.out.println(personaToString(it.next()));
        }
    }

    /**
     * Crea una stringa contenente nome della persona e dati riguardo patologie e assunzioni
     * @param persona
     * @return stringa con informazioni sulla persona
     */
    private String personaToString(Persona persona) {
        String somministrazioni = persona.somministrazioneToString();
        return "Nome soggetto:" + persona.getNomeCompleto() + ", Somministrazioni:" + somministrazioni;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Persona next() {
        return null;
    }
}

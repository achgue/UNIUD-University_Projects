import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Farmaco {
    String nome;
    Regola regola; //serie di regole da rispettare per ogni farmaco

    Farmaco(String n, Regola r){
        nome = n;
        regola = r; //l'ideale sarebbe usare un builder
    }
    /**
     * Verifica la compatibilità tra un farmaco da somministrare e la lista degli incompatibili
     * @param f
     * @return
     */
    public boolean isCompatibile(Farmaco f){
        Iterator<Farmaco> it = regola.incompatibili.iterator();
        while(it.hasNext()){
            if(it.next().equals(f))
                return false;
        }
        return true;
    }

    public boolean isEtaMin(Persona p){
        if(p.eta < regola.etaMin){
            return false;
        }
        return true;
    }

}

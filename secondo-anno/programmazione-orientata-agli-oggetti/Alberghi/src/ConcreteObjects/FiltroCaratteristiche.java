package ConcreteObjects;

import caratteristiche.Caratteristica;

import java.util.List;

public class FiltroCaratteristiche<T>{
    List<Caratteristica> caratteristiche;

    public boolean soddisfaCaratteristiche(T t){
        for(Caratteristica c : caratteristiche){
            if(!c.èSoddisfatta(t))
                return false;
        }
        return true;
    }

    public void aggiungiCaratteristica(Caratteristica caratteristica){
        caratteristiche.add(caratteristica);
    }

}

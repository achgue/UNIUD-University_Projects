package ConcreteObjects;

import java.util.ArrayList;
import java.util.Collection;

public class FiltroGenerico {

    public static <T> Collection<T> filtra(Collection<T> lista, FiltroCaratteristiche caratteristiche) {
        Collection<T> ris = new ArrayList<T>();
        for (T t : lista) {
            if(caratteristiche.soddisfaCaratteristiche(t))
                ris.add(t);
        }
        return ris;
    }
}

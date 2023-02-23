package caratteristiche.albergo;

import ConcreteObjects.Albergo;
import caratteristiche.Caratteristica;

public class haSaune implements Caratteristica<Albergo> {
    private int n;
    haSaune(int n){
        this.n = n;
    }

    @Override
    public boolean èSoddisfatta(Albergo albergo) {
        return false;
    }
}

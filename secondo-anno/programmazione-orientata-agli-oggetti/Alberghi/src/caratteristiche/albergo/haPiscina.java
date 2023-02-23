package caratteristiche.albergo;

import ConcreteObjects.Albergo;
import caratteristiche.Caratteristica;

public class haPiscina implements Caratteristica<Albergo> {
    private int n;
    haPiscina(int n){
        this.n = n;
    }

    @Override
    public boolean èSoddisfatta(Albergo albergo) {
        return false;
    }
}

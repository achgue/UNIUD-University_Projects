package caratteristiche.albergo;

import ConcreteObjects.Albergo;
import caratteristiche.Caratteristica;

public class haMassaggi implements Caratteristica<Albergo> {
    private int n;
    haMassaggi(int n){
        this.n = n;
    }

    @Override
    public boolean èSoddisfatta(Albergo albergo) {
        return false;
    }
}

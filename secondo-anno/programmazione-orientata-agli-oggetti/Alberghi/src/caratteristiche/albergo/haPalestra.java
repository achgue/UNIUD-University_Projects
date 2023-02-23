package caratteristiche.albergo;

import ConcreteObjects.Albergo;
import caratteristiche.Caratteristica;

public class haPalestra implements Caratteristica<Albergo> {
    private int n;
    haPalestra(int n){
        this.n = n;
    }

    @Override
    public boolean èSoddisfatta(Albergo albergo) {
        return false;
    }
}

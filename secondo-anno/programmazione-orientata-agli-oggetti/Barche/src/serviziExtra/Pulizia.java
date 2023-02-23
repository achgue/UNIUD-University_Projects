package serviziExtra;

public class Pulizia implements Extra {
    private final int costo;

    public Pulizia(int costo) {
        this.costo = costo;
    }

    @Override
    public int getCosto() {
        return 0;
    }
}

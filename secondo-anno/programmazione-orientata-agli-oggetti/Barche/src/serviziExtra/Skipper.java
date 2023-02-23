package serviziExtra;

public class Skipper implements Extra {
    private final int costo;

    public Skipper(int costo) {
        this.costo = costo;
    }

    @Override
    public int getCosto() {
        return 0;
    }
}

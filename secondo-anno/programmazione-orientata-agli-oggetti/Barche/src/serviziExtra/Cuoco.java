package serviziExtra;

public class Cuoco implements Extra {
    private final int costo;

    public Cuoco(int costo) {
        this.costo = costo;

    }

    @Override
    public int getCosto() {
        return 0;
    }
}

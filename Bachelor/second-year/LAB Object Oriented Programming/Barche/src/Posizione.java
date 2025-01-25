    /**
     * Dato astratto immutabile per rappresentare una posizione
     * Ha una coordinata x e una y
     */
    public class Posizione {
        private final float longitudine;
        private final float latitudine;


        public Posizione(float longitudine, float latitudine) {
            this.longitudine = longitudine;
            this.latitudine = latitudine;
        }

        public float ottieniLongitudine() {
            return this.longitudine;
        }

        public float ottieniLatitudine() {
            return this.latitudine;
        }
    }


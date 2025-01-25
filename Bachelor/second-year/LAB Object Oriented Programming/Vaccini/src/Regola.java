import java.util.Iterator;
import java.util.List;

/**
 * le distanze di tempo sono rappresentate in giorni o mesi
 */
public class Regola {
    List<Farmaco> incompatibili;
    int etaMin;
    int distanzaMinProxAssunzione;
    int distanzaMaxProxAssunzione;
    int[] dosaggiPerEta;
    int durataEffettoCiclo; //ciclo di assunzione


    /*public boolean isEtaMin(Persona p){

    }
    public boolean isEtaMin(Persona p){

    }*/



    private Regola(RegolaBuilder rb){
        this.incompatibili = null;
        this.etaMin = rb.etaMin;
        this.distanzaMinProxAssunzione = rb.distanzaMinProxAssunzione;
        this.distanzaMaxProxAssunzione = rb.distanzaMaxProxAssunzione;
        this.dosaggiPerEta= rb.dosaggiPerEta;
        this.durataEffettoCiclo = rb.durataEffettoCiclo;
    }

    public static class RegolaBuilder {
        public int etaMin;
        public int distanzaMinProxAssunzione;
        public int distanzaMaxProxAssunzione;
        public int[] dosaggiPerEta;
        public int durataEffettoCiclo;

        public RegolaBuilder(int etaMin) {
            this.etaMin = etaMin;
        }

        public RegolaBuilder conDistanzaAssunzione(int distanzaMinProxAssunzione, int distanzaMaxProxAssunzione) {
            this.distanzaMinProxAssunzione = distanzaMinProxAssunzione;
            this.distanzaMaxProxAssunzione = distanzaMaxProxAssunzione;
            return this;
        }

        public RegolaBuilder conDosaggiPerEta(int[] dosaggiPerEta) {
            this.dosaggiPerEta = dosaggiPerEta;
            return this;
        }

        public RegolaBuilder conDurataEffettoCiclo(int durataEffettoCiclo) {
            this.durataEffettoCiclo = durataEffettoCiclo;
            return this;
        }

        //the actual build method that prepares and returns a BankAccount object
        public Regola build() {
            return new Regola(this);
        }

    }
}
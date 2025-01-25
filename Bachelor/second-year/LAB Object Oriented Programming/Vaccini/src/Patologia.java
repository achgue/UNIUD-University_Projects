import java.util.List;

public class Patologia {
    String nome;
    List<Farmaco> curataDa;
    //Dosi richieste per cura

    Patologia(String nome){
        this.nome = nome;
        curataDa = null;
    }

    public void aggiungiCura(Farmaco f){
        curataDa.add(f);
    }
}

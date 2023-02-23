import java.util.Iterator;

public class Main {
    public static void main(String[] args){

        Persona p1 = new Persona("ach", "gue", 21);
        Persona p2 = new Persona("mamma", "ant", 12);
        Persona p3 = new Persona("gianluca", "sticazzi", 69);
        Persona p4 = new Persona("gabri", "breb", 25);
        Persona p5 = new Persona("chair", "pingu", 30);
        Persona p6 = new Persona("Xenonno", "smadonno", 90);

        Patologia patologia1 = new Patologia("Sesso");

        p1.aggiungiPatologia(patologia1);

        Regola regolaX = new Regola.RegolaBuilder(13)
                .conDistanzaAssunzione(5, 5)
                .conDosaggiPerEta(new int[]{3, 5 , 8, 8})
                .build();
        Regola regolaY = new Regola.RegolaBuilder(3).build();

        Farmaco farmacoX = new Farmaco("Pillole",regolaX);
        Farmaco farmacoY = new Farmaco("Siringhe", regolaY);

        farmacoX.regola.incompatibili.add(farmacoY);

        Assunzione assunzioneY = new Assunzione(farmacoY, "15/03/2000");
        p4.aggiungiAssunzione(assunzioneY);

        Assunzione assunzioneX = new Assunzione(farmacoX, "12/05/2000");
        p1.aggiungiAssunzione(assunzioneX);
        p2.aggiungiAssunzione(assunzioneX);
        p4.aggiungiAssunzione(assunzioneX);
        p6.aggiungiAssunzione(assunzioneX);



        Iterator<Persona> it = RegistroSomministrazioni.elencoAssunzioni.iterator();
        while(it.hasNext()){
            System.out.println(it.next().nome);*/
        }
    }
}

/**
 * Aggiungi qui una descrizione della classe EsDieci
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public class btrSucc
{
    public static StringSList btrListSucc(String btr, int n) //ritorna lista degli n numeri sucessivi a btr
    {
        StringSList ris = new StringSList();
        ris = ris.cons(btr);
        for(int i = n; i > 1; i--){ //devo generare n-1 elementi quindi 1 iterazione in meno
            ris = ris.cons(btrSucc(ris.car()));
        }
        ris = ris.reverse();
        return ris;
    }
    
    
    
    
        public static String btrSucc(String str)
    {
        int n = str.length();
        char simb = str.charAt(n - 1);
        String nov = str.substring(0, n - 1);
        if(n == 1){
            if(simb == '+'){
                return "+-";
            }else{
                return "+";
            }
        }else{
            if(simb == '+'){
                return btrSucc(nov) + "-";
            }else{
                return simb == '-' ? nov  + "." : nov  + "+";
            }
        }
    }
}

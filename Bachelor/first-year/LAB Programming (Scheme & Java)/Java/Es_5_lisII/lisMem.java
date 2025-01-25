//topDown
/**
 * Aggiungi qui una descrizione della classe sdf
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public class lisMem
{
    private static final int UNKNOWN = 0;
    
    public static int llis( int[] s ) { // s[i] > 0 per i in [0,n-1], dove n = s.length
        int n = s.length;
        int[][] mem = new int [n+1][n+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=n; j++){
                mem[i][j]=UNKNOWN;
            }
        }
        return llisRec( s, 0, 1, mem);
    }

    private static int llisRec( int[] s, int i, int t, int[][]mem) { //t=indice del secondo array
        int m = s.length;
        int n = m;
        if(mem[i][t] == UNKNOWN){
            if((i==m) || (t==n)){
                mem[i][t] = 0;
            }else if(s[i] == (t)){
                mem[i][t] = 1 + llisRec(s, i+1, t+1 , mem);
            }else{
                mem[i][t] =  Math.max(
                    llisRec(s, i+1, t, mem),
                    llisRec(s, i, t+1, mem)
                );
            }
        }
        return mem[i][t];
    }  

    public static int llisII( int[] s ) { // s[i] > 0 per i in [0,n-1], dove n = s.length
        int n = s.length;
        int[][] mem = new int [n+1][n+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=n; j++){
                mem[i][j]=UNKNOWN;
            }
        }
        return llisRecII( s, 0, n, mem); //n non appartiene all'array (passiamo un valore che non ha senso)
    }

    private static int llisRecII( int[] s, int i, int j, int[][]mem ) {
        if(mem[i][j] == UNKNOWN){
            if(i == s.length){ //abbiamo analizzato tutto l'array
                mem[i][j] = 0;
                
                //la prima parte della condizione permette che nella prima iterazione sisalti subito al passo sucessivo
            } else if(s.length != j && s[i] <= s[j]){ //j indica l'indice del numero 
                mem[i][j] = llisRecII( s, i+1, j, mem);    
            }else{
                mem[i][j] = Math.max( 
                                1+llisRecII(s,i+1,i,mem), 
                                llisRecII(s,i+1,j,mem) 
                                );
                }    //scabio i e j se j>i (sequenza crescente)
            }
        return mem[i][j];
    }
}

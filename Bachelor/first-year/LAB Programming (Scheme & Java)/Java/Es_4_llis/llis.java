/**
 * Aggiungi qui una descrizione della classe llis
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public class llis
{
    private static final int UNKNOWN = 0;
    public static int llis( int[] s ) { 
        int n = s.length;
        int[][] mem = new int [n+1][n+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=n; j++){
                mem[i][j]=UNKNOWN;
            }
        }
        return llisRec( s, 0, 0, mem);
    }

    private static int llisRec( int[] s, int i, int t, int[][]mem ) {
        if(mem[i][t] == UNKNOWN){
            if(i == s.length){

                mem[i][t] = 0;

            } else if(s[i] <= t){  
                mem[i][t] = llisRec( s, i+1, t, mem);    
            }else{
                mem[i][t] = Math.max( 
                    1+llisRec(s, i+1, s[i], mem), 
                    llisRec(s, i+1, t ,mem) 
                );
            }    
        }
        return mem[i][t];
    }
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------

    // NB: seconda versione della prima parte del problema risolta secondo la "logica del della sottostringa più lunga" (se ha bisogno di info mi contatti)

    public static int llis2( int[] s ) { 
        int n = s.length;
        int[][] mem = new int [n+1][n+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=n; j++){
                mem[i][j]=UNKNOWN;
            }
        }
        return llisRec2( s, 0, 1, mem);
    }

    private static int llisRec2( int[] s, int i, int t, int[][]mem) {
        int m = s.length;
        if(mem[i][t] == UNKNOWN){
            if((i==m) || (t==m)){
                mem[i][t] = 0;
            }else if(s[i] == (t)){
                mem[i][t] = 1 + llisRec2(s, i+1, t+1 , mem);
            }else{
                mem[i][t] =  Math.max(
                    llisRec2(s, i+1, t, mem),
                    llisRec2(s, i, t+1, mem)
                );
            }
        }
        return mem[i][t];
    }
}

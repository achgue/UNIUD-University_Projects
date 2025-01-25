public class llds
{
    public static int llds( int[] s ) { // s[i] > 0 per i in [0,n-1], dove n = s.length
        int t = 0;
        for(int i = 0; i<s.length; i++){
            if (t < s[i]){
                t = s[i]+1;
            }
        }
        return lldsRec( s, 0, t );
    }

    private static int lldsRec( int[] s, int i, int t ) {
        if ( i == s.length ) { //coda di s vuota
            return 0;
        } else if ( s[i] >= t ) { // il primo elemento è sempre contato, salto direttamente all'else
            return lldsRec( s, i+1, t );
        } else { //s[i] può essere scelto (+1) o meno(mantengo stessa soglia)
            return Math.max( 1+lldsRec(s,i+1,s[i]), lldsRec(s,i+1,t) );
        }
    }


    
    public static int lldsDP( int[] s ) {

        int n = s.length;

        int[][] mem = new int[ n+1 ][ n+1 ];

        // Matrice: valori delle ricorsioni di llisRec
        // relativi a diversi valori degli argomenti
        for ( int j=0; j<=n; j=j+1 ) {
            mem[n][j]=0;
        }
        
        
        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {
                if(j < i || j == n){ //escludo la parte grigia
                    int t = ( j == n ) ? 0 : s[j];
                    if(s[i] < t || j == n){
                        mem[i][j] = Math.max(mem[i+1][i]+1,mem[i+1][j]);
                    }else{
                        mem[i][j] = mem[i+1][j];
                    }
                }
            }
        }
        

        //{2,7,5,7,4}

        // ----------------------------------------------------
        //  Inserisci di seguito l'elemento della matrice
        //  il cui valore corrisponde a llis(s) :

        return  mem[0][n];

        // ----------------------------------------------------
    }
    

    
    public static int[] ldsDP( int[] s ) {

        int n = s.length;

        int[][] mem = new int[ n+1 ][ n+1 ];

        // 1. Matrice: valori delle ricorsioni di llisRec
        //    calcolati esattamente come per llisDP

        for ( int j=0; j<=n; j=j+1 ) {
            mem[n][j]=0;
        }
        int k;
        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {
                if(j < i || j == n){ //escludo la parte grigia
                    int t = ( j == n ) ? 0 : s[j];
                    if(s[i] < t || t == 0){
                        mem[i][j] = Math.max(mem[i+1][i]+1,mem[i+1][j]);
                    }else{
                        mem[i][j] = mem[i+1][j];
                    }
                }
            }
        }

        // 2. Cammino attraverso la matrice per ricostruire
        //    un esempio di Longest Increasing Subsequence

        // ----------------------------------------------------
        //  Inserisci di seguito l'elemento della matrice
        //  il cui valore corrisponde a llis(s) :

        int m = mem[0][n];

        // ----------------------------------------------------

        int[] r = new int[ m ];  // per rappresentare una possibile LIS

        // ----------------------------------------------------
        //  Introduci e inizializza qui gli indici utili
        //  per seguire un cammino attraverso la matrice e
        //  per assegnare gli elementi della sottosequenza r
        // ----------------------------------------------------
        int i=0;
        int j=n;
        int x=0;
        while ( mem[i][j] > 0 ) {

            int t = ( j == n ) ? 0 : s[j]; //o cambio lo zero in un valore molto grande o inserisco controllo per j==n

                if(mem[i+1][i]+1 >= mem[i+1][j] && (j == n || s[i] < t) ){ //controllo in modo da valutare ultima riga
                //il secondo controllo è molto simile a ciò che facciamo quando ricerchiamo il numero del'array maggiore
                //in particolare da ricordarsi che s[i]<t perchè cerchiamo sottostringhe decrescenti
                r[x] =  s[i];
                j=i; //mi muovo in diagonale
                x++;
            }
            i++;//mi muovo in orrizontale
        }

        return r;                // = LIS relativa alla sequenza s
    }
}
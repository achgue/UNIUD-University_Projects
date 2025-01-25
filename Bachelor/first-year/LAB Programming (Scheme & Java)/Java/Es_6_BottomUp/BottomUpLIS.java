
public class BottomUpLIS {

    // Length of Longest Increasing Subsequence (LLIS):
    // Programmazione dinamica bottom-up

    public static int llisDP( int[] s ) {

        int n = s.length;

        int[][] mem = new int[ n+1 ][ n+1 ];

        // Matrice: valori delle ricorsioni di llisRe
        // relativi a diversi valori degli argomenti
        for ( int j=0; j<=n; j=j+1 ) {
            mem[n][j]=0;
        }
        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {
                if(j < i || j == n){ //escludo la parte grigia
                    int t = ( j == n ) ? 0 : s[j];
                    if(s[i] > t){
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

    // Longest Increasing Subsequence (LIS):
    // Programmazione dinamica bottom-up

    public static int[] lisDP( int[] s ) {

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
                    k = ( j == n ) ? 0 : s[j];
                    if(s[i] > k){
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

            int t = ( j == n ) ? 0 : s[j];
            //se metto uguaglianza stretta mi prende l'ultima sottosequenza più lunga
            if(mem[i+1][i]+1 >= mem[i+1][j] && s[i] > t){
                //controllo s[i]>t perchè cerco sottostinge crescenti (per andare in diagonale devo verificare se posso prendere il numero)
                //verifico se il numero è più grande di quelli che ho già preso
                r[x] =  s[i];
                j=i; //mi muovo in diagonale
                x++;
            }
            i++;//mi muovo in orrizontale
        }
    
        return r;                // = LIS relativa alla sequenza s
    }

}  // class BottomUpLIS

import java.util.function.*;


public class Board {


  // Codifica secondo le convenzioni scacchistiche (massima dimensione: 15 x 15)
  
  private static final String ROWS = " 123456789ABCDEF";
  private static final String COLS = " abcdefghijklmno";
  
  
  // Realizzazione del dato astratto "Scacchiera": stato interno
  
  private final int size;                             // 1) dimensione scacchiera
  private final int queens;                           // 2) numero regine collocate
  
  private final SList codRighe, codColonne, diagDestra, diagSinistra;
  
  private final String config;                        // 4) disposizione delle regine:
                                                      //    rappresentazione testuale
  
  // Costruttori:
  
  public Board( int n ) {                             // scacchiera vuota
  
    size = n;                                         // scacchiera n x n
    queens = 0;                                       // nessuna regina
    codRighe = new SList<Integer>();
    codColonne = new SList<Integer>();
    diagDestra = new SList<Integer>();
    diagSinistra = new SList<Integer>();
    config = " ";
  }
  
  private Board( Board board, int i, int j ) {        // <-- board.addQueen(i,j)
    
    size = board.size();                              // stessa dimensione di board
    queens = board.queensOn() + 1;                    // ma una nuova regina che...
    
    codRighe = board.codRighe.cons(i);
    codColonne = board.codColonne.cons(j);
    diagDestra = board.diagDestra.cons(i-j);
    diagSinistra = board.diagSinistra.cons(i+j);
    /*attack = ( u, v ) -> ( (u == i) ||                // minaccia la riga i
                           (v == j) ||                // minaccia la colonna j
                           (u-v == i-j) ||            // minaccia le diagonali i-j e i+j
                           (u+v == i+j) ||
                           board.underAttack(u,v)     // minaccia delle regine in board
                           );*/
    
    config = board.arrangement() + COLS.charAt(j) + ROWS.charAt(i) + " ";
  }
  
  
  public int size() {                                 // dimensione della scacchiera
      
    return size;
  }
  
  
  public int queensOn() {                             // numero di regine collocate
  
    return queens;
  }
  
  
  public boolean underAttack( int i, int j ) {        // posizione <i,j> minacciata?
      for(int k = 0; k < queens; k++){
        if(codRighe.listRef(k).equals(i)){
            return true;
        }else if (codColonne.listRef(k).equals(j)){
            return true;
        }else if(diagDestra.listRef(k).equals(i-j)){
            return true;
        }else if(diagSinistra.listRef(k).equals(i+j)){
            return true;
        }   
    }
    return false; //in questo caso funziona ma sarebbe meglio assegnare un valore a una variabile che viene modificato all'interno degli if e ritornalo
  }
  
  
  public Board addQueen( int i, int j ) {             // nuova scacchiera
                                                      // con una regina anche in <i,j>
    return new Board( this, i, j );
  }
  
  
  public String arrangement() {                       // descrizione testuale
  
    return config;
  }
  
  
  public String toString() {                          // rappresentazione standard per Java
  
    return "[" + config + "]";
  }


}  // class Board

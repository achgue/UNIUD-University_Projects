import queens.*;

public class Queens {
    public static final SList<Board> NULL_BOARDLIST = new SList<Board>();
    
    public static void prova(int n){
        ChessboardView view = new ChessboardView(n);
        SList<Board> results = listOfAllSolutions(n);
        for(int k = 0; k < results.length(); k++){
            view.setQueens(results.listRef(k).arrangement());   
        }
    }
    private static int numberOfCompletions( Board b ) {
    
        int n = b.size();
        int q = b.queensOn();
    
        if ( q == n ) {
    
            return 1;
    
        } else {
    
            int i = q + 1;
            int count = 0;
    
            for ( int j=1; j<=n; j=j+1 ) {
                if ( !b.underAttack(i,j) ) {
    
                    count = count + numberOfCompletions( b.addQueen(i,j) );
                }
            }
            return count;
        }
    }
    public static SList<Board> listOfAllSolutions( int n ) {
  
        return listOfAllCompletions( new Board(n) );
    }
    
    private static SList<Board> listOfAllCompletions( Board b ) {
  
    int n = b.size();
    int q = b.queensOn();
    
    if ( q == n ) {
    
      return ( NULL_BOARDLIST.cons(b) );
    
    } else {
    
      int i = q + 1;
      SList<Board> solutions = NULL_BOARDLIST;
      
      for ( int j=1; j<=n; j=j+1 ) {
        if ( !b.underAttack(i,j) ) {
        
          solutions = solutions.append( listOfAllCompletions(b.addQueen(i,j)) );
      }}
      return solutions;
    }
  }
  
  
  // Eventuale programma principale
  
  public static void main( String args[] ) {
  
    int n = Integer.parseInt( args[0] );
    }


}  // class Queens

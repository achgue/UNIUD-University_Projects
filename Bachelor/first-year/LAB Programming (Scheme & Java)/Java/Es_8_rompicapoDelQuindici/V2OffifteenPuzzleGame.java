import puzzleboard.*;
public class V2OffifteenPuzzleGame
{
    public V2OffifteenPuzzleGame(int n){
        Board b = new Board(n);
        PuzzleBoard gui = new PuzzleBoard( n );
        int k = 0;
        int zero[] = new int[2];
        int nToMove[] = new int[2];
        int moves;
        for(int i = 1; i <= b.rowSize; i++){
            for(int j = 1; j <= b.rowSize; j++){
                gui.setNumber( i,j, b.board[k] );
                if(b.board[k] == 0){
                    zero[0] = i;
                    zero[1] = j;
                }
                k++;
            }
        }
        while(!b.isOrdered()){
            int t = gui.get();
            moves = 0;
            k=0;
            for(int i = 1; i <= b.rowSize; i++){
                for(int j = 1; j <= b.rowSize; j++){ //la condizione del k serve per verificare che siamo giunti alla riga e colonna coretta
                    if(b.board[k] == t && b.canMove(t) && moves == 0){ //?portare fuori condizione di spostamento e mandenere solo aggionamento parametri interni ai for
                        //if(){
                        moves = 1; //abbiamo fatto una mossa (seve per fare in modo che ci sia una mossa per turno)
                        nToMove[0] = i;
                        nToMove[1] = j;
                        b.move(t);//aggiorno l'array
                        gui.clear( nToMove[0], nToMove[1]);
                        gui.setNumber( zero[0],zero[1], t );
                        zero[0] = nToMove[0];
                        zero[1] = nToMove[1];
                        gui.display();
                        //}
                    }
                    k++;
                }
            }
            //.out.println(""+nToMove[0]+" "+nToMove[1]);
        }
    }
}

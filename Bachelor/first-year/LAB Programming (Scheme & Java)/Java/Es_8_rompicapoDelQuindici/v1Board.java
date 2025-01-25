public class v1Board{
    public final int size;
    public int[] board;
    public int free;
    public final int rowSize;

    public v1Board(int n){
        size = (n*n);
        rowSize = n;
        board = initialize(size);
        for(int i = 0; i < size; i++){
            if(board[i] == 0)
                free = i;
        }
        permutation();
    }

    //genera tabella
    private static int[] initialize(int n){
        int [] b = new int[n];
        for(int i = 0; i < n-1; i++){
            b[i] = i+1;
        }
        return b;
    }
    //combinazione casuale della tabella
    public void permutation(){ //modivica stato iniziale della board
        for(int j = 0; j < size*size; j++){
            int k = (int)(Math.random()*(size)-1);
            move(k);
        }
        for(int i = 0; i < size; i++){
            if(board[i] == 0)
                free = i;
        }
    }

    public boolean isOrdered(){
        if(isSorted(board) && board[size-1] == 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isSorted(int[] a)
    {
        for (int i = 1; i < a.length - 1; i++)
        {
            if (a[i-1] > a[i]) {
                return false;
            }
        }

        return true;
    }

    //sistemare la suluzione in base ad associazione tra numeri e indici    

    public boolean canMove(int i){
        if(free+rowSize == i || free-rowSize == i){  //mi muovo sopre o sotto
            return true;
        }else if(Math.min(free,i)%rowSize != rowSize-1 && free-1 == i){ //ci muoviamo se la cella precedente rispetto a free è == i
            return true;
        }else if(Math.min(free,i)%rowSize != rowSize-1 && free+1 == i){
            return true;
        }else{
            return false;
        }
    }

    /*public boolean canMove(int i){
    /*if((i+1)%rowSize == 0 || (i-1)%rowSize == 0){
    if(free-1 == i || free+1 == 1){
    return false; //mi trovo sull'estremità del mio array
    }else{
    return true; //
    }
    }else
    if(free+rowSize == i || free-rowSize == i){
    return true;
    }else if((free+1 == i || free-1 == i) && (free-i != 1 || (-free)+i != 1)){
    return true;
    }else{
    return false;
    }
    }*/

    public String config(){
        String res = "";
        for(int i = 0; i < board.length; i++){
            res = res + board[i] + " ; ";
        }
        return res;
    }

    public void move(int i){ //muove elemento in posizione i su free
        if(canMove(i)){
            int k = board[i];
            board[i] = board[free];
            board[free] = k;
            free = i;
        }
    }
}
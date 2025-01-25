public class NodeStack{
    /*
        2 tipo di stutture
        - filo : first in last out (esempio dello stack)
        - fifo : first in first out
    */
    public static int size;
    public static Node[] stack;
    public NodeStack(){//costruttore: creazione di uno stack di nodi vuoto
        size = 0;
        stack = new Node[size];
    }
    public boolean empty(){ //verifica se lo stack è vuoto
        if(size == 0)
            return true;
        return false;
    }
    public Node peek(){//restituisce l’elemento in cima allo stack (senza rimuoverlo dallo stack)
        return stack[size-1];
    } 
    public Node pop(){ //restituisce l’elemento in cima allo stack e lo rimuove dallo stack
        Node lastNode = peek();
        size = size - 1;
        Node[] stackNew = stack;
        stack = new Node[size];
        for(int i = 0; i < size; i++){
            stack[i] = stackNew[i];
        }
        return lastNode;
    }
    public void push( Node n ){//aggiunge un nuovo elemento n in cima allo stack
        size = size + 1;
        Node[] stackNew = stack;
        stack = new Node[size];
        for(int i = 0; i < size - 1; i++){ //riempio fino a size -2 e poi aggiungo l'ultimo elemento
            stack[i] = stackNew[i];
        }
        stack[size - 1] = n;
    }
}

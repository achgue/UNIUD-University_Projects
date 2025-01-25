public class NodeQueue
{
    public int size;
    public Node[] queue;
    
    //sarebbe una buona scelta inizializzare l'array a una lunghezza fissa e aumentare la sua grandezza dopo
    
    public NodeQueue(){
        size = 0; //grandezza iniziale della queue è 0 (vuota)
        queue = new Node[size];//creo queue vuota (array di elementi node vuoto)
    }
    
    public int size(){
        return size;
    }
    
    public Node peek(){ //restituisce l’elemento con “peso minore” (senza rimuoverlo dalla coda)
        return queue[size-1];
    }
    
    public Node poll(){ //restituisce e rimuove dalla coda l’elemento con “peso minore”
        Node minWeightNode = peek();
        size = size - 1;
        Node[] queueNew = queue;
        queue = new Node[size];
        for(int i = 0; i < size; i++){
            queue[i] = queueNew[i];
        }
        return minWeightNode;
    }
    
    public void add( Node n ) { //aggiunge un nuovo elemento n alla coda
        size = size + 1;
        Node[] queueNew = queue;
        queue = new Node[size];

        for(int i = 0; i < size - 1; i++){ //riempio fino a size -2 e poi aggiungo l'ultimo elemento
                queue[i] = queueNew[i];
        }
        queue[size - 1] = n;
        if(size > 1)
        sortQueue();
    }
    //ordino dopo aver inserito il nuovo nodo
    private void sortQueue(){
        int k = size - 1; //inizialmente k viene usato per prendere l'ultimo elemento
        int currentNodeWeight = queue[k].weight();
        //poi k viene usato per prendere l'ultimo elemento
        while(k != 0 && queue[k - 1].weight() < currentNodeWeight){ //cerco la posizione in cui inserire il mio nuovo nodo
             Node tmpNode = queue[k]; //verifico che k != dall'indice ultimo elemento (che è null visto che abbiamo aggiunto una posizione in più)
             queue[k] = queue[k-1];
             queue[k-1] = tmpNode;
             k--;
            }
    }
}

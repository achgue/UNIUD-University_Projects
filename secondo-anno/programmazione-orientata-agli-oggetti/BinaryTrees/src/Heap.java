public class Heap implements BinaryTree{

    /**
     * La nostra Heap è implementata con un array che rappresenta il nostro albero (elenco degli elementi)
     * la lunghezza del vettore è diversa dalla grandezza dell'albero
     * H.elements.size quanto il vettore è lungo (spazio occupato)
     * Heap H = new Heap ... H.length --> numero degli elementi che compongono l'albero
     */
    int[] elements;
    int size; //actual size of the array
    int length; //number of nodes (number of elements inside the heap)

    Heap(int[] arr, int len){
        elements = arr;
        length = len;
        size = arr.length;
        heapify(this, 0);
    }


//    public Heap(HeapBuilder heapBuilder) {
//        elements = heapBuilder.elements;
//        size = heapBuilder.length;
//    }
//    //@TODO valutare se eliminare questo metodo
//    @Override
//    public void build(int[] A) {
//
//    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public int getRoot() {
        return elements[0];
    }

    @Override
    public void insert(int n) {
        if(size >= length){
            elements[length-1] = n;
        }else{
            size++;
            int[] temp = new int[size];
            for (int i = 0; i < size; i++){
                temp[i] = elements[i];
            }
            temp[size] = n;
            size++;
            elements = temp;
        }
        length++; //incremento di uno gli elementi che ho inserito
        heapify(this, 0);

    }

    @Override
    public void extract() {
        int[] B = new int[size - 1];
        size--;
        for (int i = 0; i < size; i++){
            B[i] = elements[i+1];
        }
        heapify(this, 0);
        elements = B;
    }

    @Override
    public void change(int i, int k) {
        elements[i] = k;
        heapify(this, 0);
    }

    @Override
    public int left(int i) {
        return 2*i+1;
    }

    @Override
    public int right(int i) {
        return 2*i+2;
    }

    /**
     * Sistema una struttura partendo da un i-esimo nodo in modo tale che diventi una heap
     * @param H, contiene gli elementi da inserire come chiavi dei nodi
     * @param i, rappresenta l'iesimo nodo da cui iniziamo la nostra procedura di rearrangement
     */
    private void heapify(Heap H, int i){}

}

public interface BinaryTree {
//    /**
//     * costruisce un albero riordiando il vettore in modo che in
//     * posizione 2*i+1 e 2*i+2 vi siano i figli dell'iesimo nodo
//     * @param A, vettore vuoto o non
//     */
//    //void build(int[] A);

    /**
     * Ritorna il numero di nodi presenti nell'albero
     * @return A.length()
     */
    public int length();

    /**
     * Ritorna la radice dell'albero
     * @return A.getRoot()
     * @TODO l'idea sarebbe di avere getMin e getMax che ritornano la root in base al tipo di heap che implementiamo
     */
    public int getRoot();

    /**
     * inserisce un nodo all'interno dell'albero
     * @param n, chiave del nodo da inserire nell'albero
     */
    void insert(int n);

    /**
     * Rimuove la radice e sistema la struttura
     */
    void extract();

    /**
     * modifica il valore dell'i-esimo nodo con k (ex. i.key = 10 e k = 30 allora change(i, 30) rende i.key = 30)
     * @param i nodo con valore da modificare
     * @param k valore da mettere nel nodo i
     * se k non è persente nella struttura --> ValueNotFound
     * se i > A.length() or < 0 --> NoSuchIndex
     */
    void change(int i, int k);

    public int left(int i);
    public int  right(int i);
}

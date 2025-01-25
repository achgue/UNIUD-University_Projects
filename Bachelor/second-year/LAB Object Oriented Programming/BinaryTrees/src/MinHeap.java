public class MinHeap extends Heap{

    MinHeap(int[] arr, int len) {
        super(arr, len);
    }

    void swap(int[] arr, int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    /**
     * esegue heapify in modo tale che la struttura risultante sia una min heap
     */
//    @Override
    private void heapify(Heap H, int i){
        int l = left(i);
        int r = right(i);
        int temp;

        if (l <= H.size && H.elements[l] < H.elements[i]){
            temp = l;
        }else{
            temp = i;
        }
        if (r <= H.size && H.elements[r] < H.elements[i]){
            temp = r;
        }
        if (temp != i){
            swap(H.elements, temp, i);
            heapify(H, temp);
        }
    }
}

public class test
{
    public static int[] permutation(int n){
        int [] permutation = new int[n+1];
        for(int i = 0; i<n+1; i++){
            permutation[i] = i;
        }
        for(int j = 0; j < n+1; j++){
            int current  = permutation[j];
            int k = (int)(Math.random()*(n));
            int change = permutation[k];
            
            permutation[j] = change;
            permutation[k] = current;
        }
        return permutation;
    }
}

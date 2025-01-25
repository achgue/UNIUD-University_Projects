#include <stdio.h>
#include <stdlib.h>

#define MAX_LINE_SIZE 1000   // maximum size of a line of input

static int scanArray(int *a) {
    // scan line of text
    char line[MAX_LINE_SIZE];
    scanf("%[^\n]", line);

    // convert text into array
    int size = 0, offset = 0, numFilled, n;
    do {
        numFilled = sscanf(line + offset, "%d%n", &(a[size]), &n);
        if (numFilled > 0) {
            size++;
            offset += n;
        }
    } while (numFilled > 0);

    return size;
}


void searchSumWorst(int *pair, int* a, int sum, int size){ //tetha di n*n
  pair[0] = -1;
  pair[1] = -1;
  for(int i = 0; i < size; i++){
    for(int j = 0; j < size; j++){
      if(i != j && a[i]+a[j] == sum){
        pair[0] = j;
        pair[1] = i;
      }
    }
  }
}



void searchSumOptimal(int *pair, int* a, int sum, int size){ //theta di n
  pair[0] = -1;
  pair[1] = -1;
  int i = 0;
  int j = size-1;
  while (i < j) {
     if(a[i]+a[j] == sum){
            pair[0] = j;
            pair[1] = i;
            break;
        }else if(a[i]+a[j] > sum){
            j--;
        }else if(a[i]+a[j] < sum){
            i++;
        }
  }
}


int main(){
  int a[1000];
  int size = scanArray(a);
  int sum;
  scanf("%d", &sum);
  int pair[2];
  searchSumOptimal(pair ,a , sum, size);
  printf("%d %d", pair[0], pair[1]);
  return 0;
}

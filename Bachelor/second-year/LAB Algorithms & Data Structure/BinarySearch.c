/*
int binarySearchRec(int *a, int i, int j, int key){
  int i = 0;
  int j = n-1;
  if(i > j){
    int k = (i+j)/2;
    if (key == a[k])
      return k;
    else if (key < a[k])
      binarySearchRec(*a,i,k-1,key);
    else if(key > a[k])
      binarySearchRec(*a,k+1,j,key);
}
*/
#include <stdio.h>
#include <stdlib.h>

#define MAX_LINE_SIZE 1000   // maximum size of a line of input

int scanArray(int *a) { //pointer of final array
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

int binarySearch(int *a, int n, int key){
  int i = 0;
  int j = n-1;
  while(i <= j){
    int k = (i+j)/2;
    if (key == a[k])
      return k;
    else if (key < a[k])
      j = k-1;
    else if(key > a[k])
      i = k+1;
  }
  return -1;
}

int main(){
  int* arr;
  int size = scanArray(arr);
  int key;
  scanf("%d", &key);
  int pos = binarySearch(arr, size, key);
  printf("%d", pos);
  return 0;
}

#include <stdio.h>

void swap(int* x, int* y){
  int z = *x;
  *x = *y;
  *y = z;
}

void revers(int* begin, int size){
  for(int i = 0; i<size/2; i++){
    swap(begin + i; begin + size - i - 1);
  }
}
#define SIZE 11

int main(){
  while(read < SIZE)
}

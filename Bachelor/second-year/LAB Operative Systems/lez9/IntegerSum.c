#include <stdio.h>

int main(){
  int n = 0;
  int sum = 0;
  while (scanf("%d", &n) == 1){ //visto che scanf torna il numero di argomenti passati io verifico che questo sia uaguale a uno in quel caso continuo a sommare altrimeti no
      sum += n;
    printf(" %d\n", sum);
  }
}

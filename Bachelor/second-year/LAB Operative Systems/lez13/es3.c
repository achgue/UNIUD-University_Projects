#include <stdio.h>

#define ITEMS 10

int main()
{
    //apro il file urandom (contiene numeri generati randomicamente)
  FILE *devrandom = fopen("/dev/urandom", "rb");
  if(!devrandom) {
    perror("Impossibile aprire il file /dev/urandom");
    return 1;
  }
  //inzializzo il vettore data in cui inserire i miei 10 numeri
  int data[ITEMS] = { 0 };
  //inserisco nel mio vettore la lettrura dei 10 numeri attraverso fread
  int items = fread(data, sizeof(int), ITEMS, devrandom);

  // ATTENZIONE: tratto EOF come un errore perché /dev/urandom non dovrebbe
  //             avere una fine.
  if(items != ITEMS) {
    perror("Errore durante la lettura del file /dev/urandom");
    return 2;
  }

  //stampo i numeri
  for(int i = 0; i < ITEMS; ++i) {
    printf("%d\n", data[i]);
  }

  fclose(devrandom);

  return 0;
}

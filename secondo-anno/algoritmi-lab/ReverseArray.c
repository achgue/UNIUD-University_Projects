#include <stdio.h>
#include <string.h>
#include <math.h>

void swap(int *x, int *y){
  int z;
  z = *x;
  *x = *y;
  *y = z;
}

int main(){
  int a[] = {1,2,3,4,5};
  //scanf("%[^\n]s", s);
  //int l = strlen(s);
  for(int i=0, j = 4; i < floor(5/2); i++,j--){
    //printf("%d - %d\n", i, j);
    swap(&(a[i]), &(a[j]));
  }
  for(int loop = 0; loop < 5; loop++)
      printf("%d ", a[loop]);
  putchar('\n');
  return 0;
}

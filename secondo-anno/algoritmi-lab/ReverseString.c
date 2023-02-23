#include <stdio.h>
#include <string.h>

void swap(char *x, char *y){
  char z;
  z = *x;
  *x = *y;
  *y = z;
}

int main(){
  char s[1000];
  scanf("%[^\n]s", s);

  int l = strlen(s);
  for(int i=0, j = l-1; i < floor(l/2); i++,j--){
    //printf("%d - %d\n", i, j);
    swap(&(s[i]), &(s[j]));
  }
  printf("%s\n", s);
  return 0;
}

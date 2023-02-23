// periodo intero : numero intero p > 0 tale che in una stringa S lunga p questo periodo si ripete per k volte con k e N
//periodo frazionario (minimo): date le stesse condizioni p è un periodo frazionario si S il pattern compare k volte più una frazione di k

// t è un bordo di s se:
//  -t è prefisso proprio (t = s[0...r-1]) ---per r = |t| n = |n|  r < n
//  -t è suffisso proprio(t = s[n-r...n-1])
// da ciò deduciamo che p è periodo frazionario sse r = n-p è lunghezza di un bordo s

#include <stdio.h>

//Soluzione quadratica

int getPeriod(char *s){
  int n = strlen(s);
  for(int p = 1; p <= n; p++){
    int good = 1;
    for(int i = 0; (i<n) && good; i++){
      if (s[i] != s[i%p])
        good = 0;
    }
    if(good)
      return p;
  }
  //return ?
}

//solizione lineare



int int main() {
  char s[100];

  scanf("%[^\n]s", s);

  int p = getPeriod(s);

  printf("%d", p);

  return 0;
}

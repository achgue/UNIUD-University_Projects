#include <stdio.h>
#include <string.h>

char *my_strchr(char*, char);
char *my_strstr(char*, char*);

char *my_strchr(char *str, char c){
  for(char *p = str; *p; ++p)
  if(*p == c)
    return p;

    return NULL;
}

char *my_strstr(char *str, char *pattern){
  int patLen = strlen(pattern);

char *p = strchr(str, pattern[0]);
  while(p) {
    if(strncmp(p, pattern, patLen) == 0)
      return p;

    p = strchr(p + 1, pattern[0]);
  }

  return NULL;
}

/*char* strchrFunction(char *str, char c){
  char* p;
  for(p = str; *p; ++p){
    //printf("%c\n", *p);
    if(*p == c){
      //printf("%c\n", 'e');
      return p;
    }
  }
}*/

int main() {
  char str[1000];
  char c;
  scanf("%s\n", str);
  scanf("%c", &c);
  //printf("%s\n", str);
  //printf("%c\n\n", c);
  char* p = my_strchr(str, c);
  printf("%p\n", p);
  //printf("%c\n", *p);

  return 0;
}

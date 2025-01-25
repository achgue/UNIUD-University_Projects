#include <stdio.h>

int readline(char *line, unsigned len) {
  int i = 0;
  int c = getchar();

  if(c == EOF)
    return EOF;

  while(c != EOF && c != '\n' && i < len - 1)
  {
    line[i] = c;

    ++i;
    c = getchar();
  }

  line[i] = 0;

  return i;
}

#define MAXLINE 100

int main() {

  char line[MAXLINE] = { };

  while(readline(line, MAXLINE) != EOF) {
    printf("Testo immesso: \"%s\"\n", line);
  }

  return 0;
}

#include <stdio.h>

char to_upper(char);
void capitalize(char *);
int readline(char *, unsigned);
int is_whitespace(char);

#define MAXLINE 100

int main()
{
  char line[MAXLINE] = { };

  while(readline(line, MAXLINE) != -1) {
    capitalize(line);
    printf("Testo immesso in Caps: \"%s\"\n", line);
  }

  return 0;
}

void capitalize(char *str)
{
  int parola_iniziata = 0;
  for(int i = 0; str[i]; ++i)
  {
    if(is_whitespace(str[i]))
    {
      parola_iniziata = 0;
    } else if (parola_iniziata == 0)
    {
      str[i] = to_upper(str[i]);
      parola_iniziata = 1;
    }
  }
}

char to_upper(char c) {
  if(c >= 'a' && c <= 'z') {
    return c - ('a' - 'A');
  }
  return c;
}

/*
 * From exercise 2
 */
int readline(char *line, unsigned len) {
  int i = 0;
  int c = getchar();

  if(c == EOF)
    return -1;

  while(c != EOF && c != '\n' && i < len - 1)
  {
    line[i] = c;

    ++i;
    c = getchar();
  }

  line[i] = 0;

  return i;
}

/*
 * From exercises of lesson 8
 */
int is_whitespace(char c) {
  return (c == ' ' || c == '\n' || c == '\t');
}

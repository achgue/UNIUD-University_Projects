#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <sys/wait.h>

#include "freadline.h"

void exec_line(char *line, int nline);
void exec_cmd(char **argv, int in, int out);
char **split(char *str);

int main(int argc, char **argv)
{
  if(argc < 2) {
    fprintf(stderr, "Specificare il nome di un file\n");
    return 1;
  }

  FILE *file = fopen(argv[1], "r");
  if(!file) {
    fprintf(stderr, "Impossibile aprire il file %s: %s\n",
            argv[1], strerror(errno));
  }

  char *line = NULL;
  int n = 0;

  while(1)
  {
    ++n;
    line = freadline(file, line);
    if(!line) {
      if(ferror(file)) {
        perror("Errore di lettura");
        return 2;
      }
      return 0;
    }

    pid_t pid=fork();
    switch(pid) {
      case -1:
        perror("fork()");
        return 3;
      case 0:
        fclose(file);
        exec_line(line, n);
      default:
        waitpid(pid,NULL,0);
    }
  }

  fclose(file);
  return 0;
}

void exec_line(char *line, int nline)
{
  char *first = line;
  char *second = NULL;

  // prima salto qualsiasi cosa che sia uno spazio all'inizio della riga
  while(isspace(*line))
    ++line;

  if(*line == '\0') // in modo da poter accorgermi di linee vuote ed ignorarle
    exit(0);

  if(*line == '|') {
    fprintf(stderr, "Manca il primo comando alla riga %d\n", nline);
    exit(3);
  }

  // trovo l'inizio del secondo comando cercando il simbolo di pipe
  char *sep = strchr(line, '|');
  if(sep == NULL) {
    fprintf(stderr, "Manca il secondo comando alla riga %d\n", nline);
    exit(3);
  }

  second = sep + 1;
  *sep = 0;

  // salto eventuali spazi o tabulazioni prima dell'inizio del secondo comando
  for(; (*second)==' ' || (*second)=='\t'; second++);

  char **argv1 = split(first);
  char **argv2 = split(second);

  int pipes[2];
  pipe(pipes);

  int pid1 = fork();
  if(pid1 == -1) {
    perror("fork()");
    exit(4);
  }

  if(pid1 == 0) { // figlio 1
    close(pipes[0]);
    exec_cmd(argv1, 0, pipes[1]);
  }

  int pid2 = fork();
  if(pid2 == -1) {
    perror("fork()");
    exit(3);
  }

  if(pid2 == 0) { // figlio 2
    close(pipes[1]);
    exec_cmd(argv2, pipes[0], 1);
  }

  close(pipes[0]);
  close(pipes[1]);

  waitpid(pid1,NULL,0);
  waitpid(pid2,NULL,0);
  free(argv1);
  free(argv2);

  exit(0);
}

void exec_cmd(char **argv, int in, int out)
{
  if(in != 0)
    dup2(in, 0);
  if(out != 1)
    dup2(out, 1);

  execvp(argv[0], argv);
  fprintf(stderr, "Impossibile eseguire %s: %s\n", argv[0], strerror(errno));
  exit(5);
}

char **split(char *str)
{
  int size = 5;
  int found = 0;
  char **strings = calloc(size, sizeof(char *));

  int parola = 0; // 1: siamo dentro ad una parola, 0: siamo in mezzo a spazi
  for(char *p = str; *p; ++p)
  {
    // se l'array è pieno lo rialloco
    if(found == size - 1) {
      size = 2 * size + 1;
      strings = realloc(strings, sizeof(char *) * size);
    }

    // All'inizio di una parola incremento 'found' e imposto il puntatore
    if(!parola && !isspace(*p)) {
      parola = 1;
      strings[found] = p;
      ++found;
    }

    if(parola && isspace(*p)) {
      parola = 0;
      *p = 0;
    }
  }

  strings[found] = NULL;

  return strings;
}

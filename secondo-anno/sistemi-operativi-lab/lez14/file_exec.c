#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

#include "freadline.h"

int exec_line(char *line, int cmdargc, char **cmdargs);
char **argsdup(int argc, char **args, char *line);

int main(int argc, char **argv)
{
  if(argc < 3) {
    fprintf(stderr, "Usage: file_exec <file> <comando...>\n");
    return 1;
  }

  char *filename = argv[1];
  int cmdargc = argc - 2;
  char **cmdargs = argv + 2;

  FILE *file = fopen(filename, "r");
  if(!file) {
    fprintf(stderr, "file_exec: %s: %s\n", filename, strerror(errno));
    return 2;
  }

  char *line = NULL;

  int n = 0;
  do {
    ++n;
    line = freadline(file, line);

    if(line) {
      if(strlen(line) == 0) // saltiamo linee vuote nel file
        continue;

      int result = exec_line(line, cmdargc, cmdargs);

      if(result == -1) {
        fprintf(stderr,
                "file_exec: impossibile eseguire il comando per la riga %d: %s",
                n, strerror(errno));
        return 3;
      }
    }
  } while(line != NULL);

  return 0;
}

int exec_line(char *line, int cmdargc, char **cmdargs)
{
  if(!line)
    return -1;

  char **newargs = argsdup(cmdargc, cmdargs, line);

  // Eseguo il comando
  int pid = fork();
  switch(pid) {
    case -1: // Errore di fork()
      fprintf(stderr, "file_exec: impossibile creare un processo figlio: %s\n",
              strerror(errno));
      free(newargs);
      return -1;

    case 0: // Processo figlio
      if(execvp(newargs[0], newargs) == -1) {
        fprintf(stderr, "file_exec: impossibile eseguire %s: %s\n",
                newargs[0], strerror(errno));
        exit(1);
      }

    default: // Processo padre
      wait(NULL);
  }

  free(newargs);
  return 0;
}

char **argsdup(int argc, char **args, char *line)
{
  // Alloco un nuovo array di argomenti
  char **newargs = (char **)malloc((argc + 1) * sizeof(char *));

  // Copio i puntatori a tutti gli argomenti così come sono tranne gli
  // argomenti che corrispondono alla stringa "@"
  for(int i = 0; i < argc; ++i) {
    if(strcmp(args[i], "@") == 0)
      newargs[i] = line;
    else
      newargs[i] = args[i];
  }

  newargs[argc] = NULL;

  return newargs;
}

#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>

int main(int argc, char **argv)
{
  if(argc < 2) {
    fprintf(stderr, "Specificare il nome di un file\n");
    return 1;
  }

  int fd = open(argv[1], O_RDONLY); //apro il file in lettura


  //errore apertura del file
  if(fd == -1) {
    fprintf(stderr, "Impossibile aprire il file %s: %s\n",
            argv[1], strerror(errno));
    return 2;
  }

    dup2(fd, 0); //riderigo il file descriptor verso std input
    execlp("cat", "cat", NULL); //eseguo cat sul file aperto

  return 0;
}

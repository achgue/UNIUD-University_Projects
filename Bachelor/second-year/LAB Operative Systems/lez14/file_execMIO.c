#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

int exec_line(char *line, int cmdargc, char **cmdargs); //esegue la funzione
char **argsdup(int argc, char **args, char *line); //crea un vettore di string contenete gli argomenti del comando




int main(int argc, char **argv){

    if(argc < 3) {
        fprintf(stderr, "Usage: file_exec <file> <comando...>\n");
        return 1;
    }

    char *filename = argv[1];
    int cmdargc = argc - 2; //command arg counter
    char **cmdargs = argv + 2; //punta agli argomenti del comando che volgio eseguire

    FILE *file = fopen(filename, "r");
    if(!file) {
        fprintf(stderr, "file_exec: %s: %s\n", filename, strerror(errno));
        return 2;
    }


    //ritornare in line la linea del file e seguire per ogni linea il comando
    char* line = NULL;
    size_t linecap = 0;

    int lineSize;

    while(lineSize = getline(&line, &linecap, file) > 0 ){ //getline rialloca lo spazio ogni volta, su linecap, che legge una nuova linea, leggo fin tanto che la dimensione della linea è >0
        //if(lineSize == 0) continue; //se la linea ha lunghezza 0
        line[lineSize-1] = '\0'; //metto il terminatore a fine linea per sostituire il a capo

        int result = exec_line(line,cmdargc,cmdargs); //mando il comando in esecuzione

        if(result == -1) {
            fprintf(stderr,
                "file_exec: impossibile eseguire il comando per la riga : %s",
                strerror(errno));
            return 3;
        }

    }
    return 0;
}

//eseguo la line passando : linea, numero argomenti del comando da eseguire, argomenti del comando
int exec_line(char *line, int cmdargc, char **cmdargs){

    char **newargs = argsdup(cmdargc, cmdargs, line); //creo un array che contiene i parametri del nuovo comando

    // Eseguo il comando
    int pid = fork(); //creo una fork per eseguire il programma e verifico il processo
    switch(pid) {
        case -1: // Errore di fork()
            fprintf(stderr, "file_exec: impossibile creare un processo figlio: %s\n",
                    strerror(errno));
            free(newargs);
            return -1;

        case 0: // se processo figlio, eseguo passando nome del comando
            if(execvp(newargs[0], newargs) == -1) { //eseguo e se ha successo torna il risultato altrimenti stampo errore
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

// creo un vettore che mi permette di avere gli argometi che mi servono per l'esecuzione
// del comando e sostituisco le riccorenze di @ con il nome del file sulla line
char **argsdup(int argc, char **args, char *line)
{
  // Alloco un nuovo array di argomenti
  char **newargs = (char **)malloc((argc + 1) * sizeof(char *)); //prendo argc e alloco memomoria +1 ovvero il terminatore
  //torna uno spazio allocato di tipo void e perciò noi facciamo il casting in char

  // Copio i puntatori a tutti gli argomenti così come sono tranne gli
  // argomenti che corrispondono alla stringa "@"
  for(int i = 0; i < argc; ++i) {
    if(strcmp(args[i], "@") == 0)//controllo se ho chiocciola sostituisco con line
      newargs[i] = line;
    else                        //altrimenti copio così com'è
      newargs[i] = args[i];
  }

  newargs[argc] = NULL; //alla fine iserisco terminatore nullo

  return newargs;
}

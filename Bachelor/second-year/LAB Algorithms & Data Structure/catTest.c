#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char **argv){

    char *filename = argv[1];

    FILE *file = fopen(filename, "r");
    if(!file) {
        fprintf(stderr, "file_exec: %s: %s\n", filename, strerror(errno));
        return 2;
    }
    char* line = NULL;
    size_t linecap = 0;

    int lineSize;

        getline(&line, &linecap, file);
        printf(line);


    return 0;
}

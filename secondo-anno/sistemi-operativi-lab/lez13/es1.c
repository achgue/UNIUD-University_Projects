#include <stdio.h>

int main(int argc, char **argv)
{
  for(int i = 1; i < argc; ++i) {
    FILE *file = fopen(argv[i], "r");

    if(!file) {
      perror("Unable to open the file");
      return 1;
    }

    for(char c = fgetc(file); c != EOF; c = fgetc(file)) {
      putchar(c);
    }

    fclose(file);
  }

  return 0;
}

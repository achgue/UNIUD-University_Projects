#include <stdio.h>
#include <string.h>

void swap(int *, int *);
void bubble(int *, int, int);
void reverse(int *, int);
int compare(int, int, int);

void swap(int *x, int *y) {
  int temp = *x;
  *x = *y;
  *y = temp;
}

int compare(int x, int y, int ascending) {
  if(ascending)
    return x <= y;
  else
    return x >= y;
}

void bubble(int *begin, int size, int ascending)
{
  for(int i = 0; i < size; i++)
  {
    for(int j = 0; j < size - i - 1; j++)
    {
      if(!compare(begin[j], begin[j + 1], ascending))
      {
        swap(begin + j, begin + j + 1);
      }
    }
  }
}

void reverse(int *begin, int size) {
  for(int i = 0; i < size / 2; ++i) {
    swap(begin + i, begin + size - i - 1);
  }
}

#define SIZE 10
//*******************************************//
int main(int argc, char **argv) {
  int values[SIZE] = { };

  if(argc < 2) {
    printf("Specificare un'opzione tra -r, -s, e -S.\n");
    return 1;
  }

  char option = 0;
  if(strcmp(argv[1], "-r") == 0)
    option = 'r';
  else if(strcmp(argv[1], "-s") == 0)
    option = 's';
  else if(strcmp(argv[1], "-S") == 0)
    option = 'S';
  else {
    printf("Specificare un'opzione tra -r, -s, e -S.\n");
    return 1;
  }

  int n = 0;
  int value = 0;
  while(n < SIZE && scanf("%d", &value) == 1) {
    values[n] = value;
    ++n;
  }

  printf("Numeri inseriti: ");
  for(int i = 0; i < SIZE; ++i) {
    printf("%d ", values[i]);
  }
  putchar('\n');

  switch(option) {
    case 'r':
      reverse(values, SIZE);
      printf("Numeri inseriti, al contrario: ");
      for(int i = 0; i < SIZE; ++i) {
        printf("%d ", values[i]);
      }
      putchar('\n');
      break;
    case 's':
      bubble(values, SIZE, 1);
      printf("Numeri inseriti, in ordine crescente: ");
      for(int i = 0; i < SIZE; ++i) {
        printf("%d ", values[i]);
      }
      putchar('\n');
      break;
    case 'S':
      bubble(values, SIZE, 0);
      printf("Numeri inseriti, in ordine decrescente: ");
      for(int i = 0; i < SIZE; ++i) {
        printf("%d ", values[i]);
      }
      putchar('\n');
      break;
  }

  return 0;
}

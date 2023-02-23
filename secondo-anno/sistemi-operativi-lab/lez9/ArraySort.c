#include <stdio.h>

#define SIZE 12

void InsertionSort(int*);

int main()
{
  int a[SIZE] = { 18, 30, 27, 20, 22, 28, 25, 25, 30, 27, 18, 26 };
  InsertionSort(a);
  for(int i = 0; i < SIZE; ++i) {
    printf("%d\n", a[i]);
  }

  return 0;
}

void InsertionSort(int* a){
    for(int i = 0; i < SIZE; i++)
    {
      for(int j = 0; j < SIZE - i - 1; j++)
      {
        if(a[j] > a[j + 1])
        {
          int temp = a[j];
          a[j] = a[j + 1];
          a[j + 1] = temp;
        }
      }
    }
}

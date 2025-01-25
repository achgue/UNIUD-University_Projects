#include <stdio.h>

#define SIZE 12

int main()
{
  int voti[SIZE] = { 18, 30, 27, 20, 22, 28, 25, 25, 30, 27, 18, 26 };

  for(int i = 0; i < SIZE; i++)
  {
    for(int j = 0; j < SIZE - i - 1; j++)
    {
      if(voti[j] > voti[j + 1])
      {
        int temp = voti[j];
        voti[j] = voti[j + 1];
        voti[j + 1] = temp;
      }
    }
  }

  for(int i = 0; i < SIZE; ++i) {
    printf("%d\n", voti[i]);
  }

  return 0;
}

#include<stdio.h>

void swap(int *x, int *y) {
  int temp = *x;
  *x = *y;
  *y = temp;
}

int partition (int arr[], int low, int high)
{
  int pivot = arr[high]; // pivot
  int i = (low - 1);

  for (int j = low; j <= high-1; j++)
  {
    if (arr[j] <= pivot)
    {
      i++;
      swap(&arr[i], &arr[j]);
    }
  }
  swap(&arr[i + 1], &arr[high]);
  return (i + 1);
}

void quickSort(int arr[], int low, int high)
{
  if (low < high)
  {
    int pi = partition(arr, low, high);
    quickSort(arr, low, pi - 1);
    quickSort(arr, pi + 1, high);
  }
}

#define SIZE 11

int main()
{
  int array[SIZE] = { };
  int read = 0;
  int n = 0;

  while(read < SIZE && scanf("%d", &n) == 1) {
    array[read] = n;
    read++;
  }

  quickSort(array, 0, read-1);

  printf("\n");
  for(int i = 0; i < read; i++) {
    printf("%d ", array[i]);
  }

  return 0;
}

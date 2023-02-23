#include <stdio.h>
#include <stdlib.h>

#include "es3.h"

struct node {
  int data;
  struct node *next;
};

struct node *create(int data) {
  struct node *ptr = malloc(sizeof(struct node));
  ptr->data = data;
  ptr->next = NULL;
  return ptr;
}

int length(struct node *head) { //ricorsivo
  if(head == NULL)
    return 0;

  return 1 + list_length(head->next);
}

struct node *find(struct node *head, int data) {
  if(head == NULL)
    return NULL;

  if(head->data == data)
    return head;

  return list_find(head->next, data);
}

struct node *last(struct node *head) {
  if(head == NULL)
    return NULL;

  if(head->next == NULL)
    return head;

  return list_last(head->next);
}
struct node *append(struct node *head1, struct node *head2) {
  if(head1 == NULL)
    return head2;

  Node *last1 = list_last(head1); //cerco l'ultimo elemento della lista1 e metto al puntatore next la il primo della lista2
  last1->next = head2;

  return head1;
}

void list_print(Node *head) {
  if(head == NULL) {
    putchar('\n');
    return;
  }

  printf("%d ", head->data);
  list_print(head->next);
}

void list_destroy(Node *head) {
  if(head == NULL)
    return;

  Node *next = head->next;

  free(head);
  list_destroy(next);
}

int main() {
  Node *list =
    list_append(list_create(3), list_append(list_create(42),
    list_append(list_create(2), list_create(4))));

  printf("Elementi della lista: ");
  list_print(list);

  printf("Lunghezza della lista: %d\n", list_length(list));

  printf("Lista a partire da 'find(list, 42)': ");
  list_print(list_find(list, 42));

  list_destroy(list);

  return 0;
}

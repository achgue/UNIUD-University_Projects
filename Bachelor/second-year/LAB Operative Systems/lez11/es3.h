/*
 * Struttura e funzioni per la manipolazione di liste concatenate
 */

struct node {
  int data;
  struct node *next;
};

struct node *create(int);
void destroy(struct node *);
int length(struct node *);
struct node *find(struct node *, int);
struct node *last(struct node *);
struct node *append(struct node *, struct node *);
void printlist(struct node *);


/*
 * Struttura e funzioni per la manipolazione di alberi binari di ricerca
 */

struct tnode {
  int data;
  struct tnode *parent;
  struct tnode *left;
  struct tnode *right;
};

struct tnode *tree_create(int data);
struct tnode *tree_insert(struct tnode *tree, int data);
struct tnode *tree_find(struct tnode *tree, int data);
struct tnode *tree_successor(struct tnode *node);
struct tnode *tree_remove(struct tnode *tree, int data);
void tree_destroy(struct tnode *tree);
void tree_print(struct tnode *tree);
struct node *tree_to_list(struct tnode *tree);
struct tnode *transplant(struct tnode *, struct tnode *);
struct tnode *tree_remove_node(struct tnode *);

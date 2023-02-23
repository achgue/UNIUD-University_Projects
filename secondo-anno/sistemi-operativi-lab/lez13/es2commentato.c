#include <stdio.h>

int main(int argc, char **argv ){
	 if(argc < 3) {
		fprintf(stderr, "Vanno specificati due file in input.\n");
		return 1;
	}
	char *filename1 = argv[1];
	char *filename2 = argv[2];
	FILE *file1 = fopen(filename1, "r");
	if(!file1){
		fprintf(stderr, "Impossibile aprire il file %s: %s\n",filename1, strerror(errno));
		return 1;
	}
	FILE *file2 = fopen(filename2, "r");
	if(!file2) {
		fprintf(stderr, "Impossibile aprire il file %s: %s\n", filename2, strerror(errno));
    		return 1;
  	}
	//inizializzo queste variabili per tenere traccia della linea incui è abbiamo individuato la differenza
	char *line1 = NULL;
	char *line2 = NULL;
	
	//faccio il confronto tramite un contatore n e esco dal while solo se le due linee sono diverse
	int n = 0;
	do {
		++n;
		line1 = fgetline(line1, file1);
		line2 = fgetline(line2, file2);
	} while(line1 != NULL && line2 != NULL && strcmp(line1, line2) == 0);


	/*for(char c1 = fgetc(file1), char c2 = fgetc(file2); c1 != EOF || c2 != EOF; c1 = fgetc(file1), c2 = fgetc(file2)){
		if(c1 != c2){
		
		}*/

	// caso 1: i due file sono finiti insieme
	if(line1 == NULL && line2 == NULL)
		printf("I due file sono identici\n");
	else { // altrimenti differiscono alla riga n
		printf("I file differiscono alla riga %d\n", n);
	//terminazione file1 
	if(line1 && line1[0])
		printf("  Riga %d in '%s': %s\n", n, filename1, line1);
	else
		printf("  Riga vuota o file '%s' terminato\n", filename1);
	}
	//terminazione file2
	if(line2 && line2[0])
		printf("  Riga %d in '%s': %s\n", n, filename2, line2);
	else
		printf("  Riga vuota o file '%s' terminato\n", filename2);
	}
	//?????
	free(line1);
	free(line2);

	return 0;
}

char *fgetline(char *line, FILE *file){

	int size = 0;
	int read = 0;

	// Se siamo già alla fine del file, restituiamo NULL e liberiamo la memoria
	if(feof(file)) {
		free(line);
		return NULL;
	}

	// Se ci hanno passato già una stringa in input riutilizzeremo la memoria,
	// quindi ci informiamo su quanto lunga è la stringa (la memoria già allocata
	// potrebbe essere di più).
	if(line != NULL)
		size = strlen(line) + 1;

	// Leggiamo un carattere alla volta riallocando quando necessario.
	for(char c = fgetc(file); c != '\n' && c != EOF; c = fgetc(file), ++read)
	{
		if(read == size) {
			size = size * 2 + 1;
			line = realloc(line, size);
	}

		line[read] = c;
	}

	// Ci assicuriamo che l'ultimo carattere contenga il terminatore nullo
	line[read] = 0;

	return line;
}


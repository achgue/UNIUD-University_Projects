#include <stdio.h>

/*int main(){
	int count = 0;
	char c = getchar();
	while(c != EOF){
		c = getchar();
		count++;
	}
	printf("%d \n", count);
	return 0;
}*/
int main(){
	int count = 1;
	char c = getchar();
	while(c != '\n'){
		c = getchar();
		if(c == ' ')
			count++;
	}
	printf("%d \n", count);
	return 0;
}

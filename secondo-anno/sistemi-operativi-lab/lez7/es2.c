#include <stdio.h>

int main(){
	int count = 0;
	char c = getchar();
	while(c != EOF){
		c = getchar();
		if(c == ' ' || c == '\n' || c == '\t')
			count++;
	}
	printf("%d \n", count);
	return 0;
}

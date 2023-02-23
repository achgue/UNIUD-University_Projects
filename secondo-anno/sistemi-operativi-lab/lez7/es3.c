#include <stdio.h>
#include <string.h>

int main(){
	int count = 0;
	char c = getchar();
	while(c != '\n'){
		c = getchar();
		if(c != ' '){
        printf("%c", '-');
    }else{
      printf("\n");
    }
	}
	return 0;
}

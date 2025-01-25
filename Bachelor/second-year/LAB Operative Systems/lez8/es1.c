#include <stdio.h>
#include <stdbool.h>

bool is_whitespace(char);

int main(){
	int count = 0;
	char c = getchar();
	while(c != EOF){
		c = getchar();
		if(is_whitespace(c))
			count++;
	}
	printf("%d \n", count);
	return 0;
}

bool is_whitespace(char c){
	if(c == ' ' || c == '\n' || c == '\t'){
		return true;
	}else{
		return false;
	}
}

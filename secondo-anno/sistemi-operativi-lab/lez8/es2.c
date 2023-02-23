#include <stdio.h>

int lg(int);
int power(int, int);
int main(){
	int n = 1000;
	int m = lg(n);
	printf("%d\n", m);
	return 0;
}

int lg(int n){
	int exp = 0;
	do{
		exp++;
	}while( power(10, exp) < n);
	return exp;
}

int power(int m, int n){
	int p = 1;
	for (int i = 0; i < n; ++i)
		p = p * m;
	return p; // restituisce il valore di p al chiamante
}

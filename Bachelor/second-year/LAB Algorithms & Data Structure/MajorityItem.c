#include <stdio.h>

int main(){
  int vec[] = {2,1,2,3,2,2,0,2};
  int c = 0;
  for(int i = 0; i < 8; i++){
    if(vec[i] == vec[i+1]){
      c++;
    }else{
      c--;
    }
  }
  printf("%d\n", c);
  return 0;
}

all: file_exec

file_exec: file_exec.o freadline.o
	gcc file_exec.o freadline.o -o file_exec

%.o: %.c freadline.h
	gcc -Wall -c -g $<

clean:
	rm -f file_exec *.o 

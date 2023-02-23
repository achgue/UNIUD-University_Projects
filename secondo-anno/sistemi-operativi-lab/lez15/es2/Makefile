
exec_pipes: exec_pipes.o freadline.o
	clang exec_pipes.o freadline.o -o exec_pipes

%.o: %.c freadline.h
	clang -c -g -o $@ $<

clean:
	rm -f *.o exec_pipes

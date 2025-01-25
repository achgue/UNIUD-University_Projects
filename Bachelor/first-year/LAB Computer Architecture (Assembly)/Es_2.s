@ ++++++++++++++Prova+++++++++++++++

@ *************** Data Segment ***************

.data
array1: .word 0,0,0,0
array2: .word 5,8,7

.set n, 10
num: .skip n*4


@ *************** Code Segment ***************

.text
main:

@ ******************************
	primi_4_n_naturali:
		ldr r0, =array1

		loop1:	
			cmp r1, #4
			beq qplo_di_n
			add r1, r1, #1
			str r1, [r0], #4
			b loop1

@ ******************************
	qplo_di_n:
		ldr r2, =array2

		loop2:
			cmp r4, #3
			beq exit
			ldr r3, [r2]
			mov r3, r3, lsl #2
			str r3, [r2], #4
			add r4, r4, #1
			b loop2

exit:
.end
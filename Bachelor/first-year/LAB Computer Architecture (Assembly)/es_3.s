@ ++++++++++++++Prova+++++++++++++++

@ *************** Data Segment ***************

.set n1, 3
num: .skip n1*4

.set a, 2
.set m, 5

.set n2, 4
v: .word 5, 8, 44, 7

.set p, 4
.set n3, 5
vett: .word 5, 8, 44, 2, 88

@ *************** Code Segment ***************

.text
main:
	primi:
		ldr r0, =num+n1*4
		ldr r1, =n1
	bl primi1


	logaritmi:
		ldr r0, =a
		ldr r1, =m
		mov r2, #0 
		mov r3, r0
	bl logaritmi1

	rimuovi_pari:
		ldr r0, =v+n2*4
		ldr r1, =n2
		ldr r3, [r0]
		mov r4, #0
	bl rimuovi_pari1

@ ******************************
	primi1:

		loop1:

			subs r1, r1, #1
			str r1, [r0, #-4]!
			bgt loop1

	end1:
		mov pc, lr

@ ******************************
 	@valutiamo il caso in cui a sia sempre minore di m

	logaritmi1:

		loop2:
		add r2, r2, #1
		mul r3, r0, r3
		cmp r3, r1
		blo loop2

	end2:
		mov r0, r2
		mov pc, lr

@ ******************************
	rimuovi_pari1:

		loop3:
			ldr r3, [r0, #-4]!
			tst r3, #0x01
			streq r4, [r0]
			subs r1, r1, #1
			bgt loop3
	end3:
		mov pc, lr

@ ******************************
	azzera_multipli:
		ldr r0, =vett
		ldr r1, =p
		ldr r2, =n3-1


		loop4:



		end4:
			mov pc, lr


@ ******************************
	
@ ******************************

exit:
.end
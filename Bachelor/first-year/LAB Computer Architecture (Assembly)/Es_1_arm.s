@ *************** Data Segment ***************

.data
num: .word -5,-20,-50,6

@ *************** Code Segment ***************

.text
main: 	ldr r5, =num 	@carico indirizzo di n1
	ldr r6, [r5]	@carico valore n1
	ldr r7, [r5,#4]	@carico valore n2
	ldr r8, [r5,#8]	@carico valore n3
	ldr r9, [r5,#12]@carico valore n4

@ ****** Esercizio 1 ******

	add r0,r6,r7	@sommo i primi due
	add r0,r0,r8	@sommo i restanti
	add r0,r0,r9

@ ****** Esercizio 2 ******

	mov r1, r0, lsr #2 	@divisione per 4 (con shift)

@ ****** Esercizio 3 ******

	add r2, r6, r6, lsl #10 	@faccio somma tra n1 e n1 shifato di 10 posizioni

@ ****** Esercizio 4 ******
	and r3, r6, #0x0f 	@(?)


@ ****** Esercizio 5 ******

	mov r4, r6, lsr #31 	@faccio shif a destra di 31 bit così mantengo quello del segno

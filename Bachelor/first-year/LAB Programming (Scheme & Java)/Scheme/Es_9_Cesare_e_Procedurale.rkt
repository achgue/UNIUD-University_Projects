;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Es_9_Cesare_e_Procedurale) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks")) #f)))
(define alf "ABCDEFGHILMNOPQRSTVX")
;;CHIAVE : QUANTO SALTARE

;mofifica di quello precedente
(define crittazione-a-cesare      ;val: stringa
  (lambda (msg regola)            ;msg: stringa di lettere maiuscole
    (if (string=? msg "")         ;regola: procedura da lettera maiuscola a lettera maiuscola
        ""
        (string-append
         (string (regola (find (string-ref msg 0) alf)))
         (crittazione-a-cesare (substring msg 1) regola)
      ))
    ))

;;GENERO LA REGOLA CHE MI SHIFTA LE LETTERE
(define rotazione-cesare   ;val: procedura
  (lambda (rot)            ;rot: intero in [0, 19]
    
  (lambda (n)              ;n: posizione del carattere
    (let ((k (+ n rot)))
      (if ( > k 19)
          (string-ref alf (- k 19))
          (string-ref alf k)
          )
      )))
   )

(define find   ;trova carattere nella stringa
  (lambda (c st) 
    (if (char=? (string-ref st 0) c)
        0
        (+ 1 (find c (substring st 1)))
        )
    ))

;----------------------------------------------------------------------------------------------;

(define i
  (lambda (x)
    x))

(define z
  (lambda (x)
    0))

(define u
  (lambda (x)
    1))

(define s2
  (lambda (u v)
    (+ 1 v)
    ))

(define H  ;torna una procedura 
  (lambda (f g)
    (lambda (m n)    ; definisco i parametri che poi userò su add, mul e pow
      (if(= n 0)
         (f m)
         (g m ((H f g) m (- n 1))) ; creo una nuova funzione ritornata da (H f g) che prende come argomenti m e n-1
         )
      )
      ))

(define add(H i s2)) ; funzione ritornara da H per la somma

(define mul(H z add)); funzione ritornara da H per la moltiplicazione

(define pow(H u mul)); funzione ritornara da H per la potenza

;ciò è possibile perchè le funzioni possono essere definite come una dipendente dall'altra
   ;ovvero :
           ; - add : calcolo del sucessivo per n volte
           ; - mul : sommo m per n volte 
           ; - pow : sommo la moltiplicazione per n volte
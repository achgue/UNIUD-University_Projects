;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Es_7_liste) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;(list-ref  '(5 7 10 18 23) 1)

;restituisce vero se x è nella lista S, altrimenti falso
(define belong?       ;val: booleano
 (lambda (x S)        ;x: intero, S: lista ordinata
   (cond ((null? S) false) 
         ((= (car S) x) true)   
         (else 
          (belong? x (cdr S)))
       )
   ))
;(belong? 18 '(5 7 10 18 23)) 

;restituisce la posizione dell'elemento nella lista
(define position   ;val: intero non negativo
  (lambda (x S)    ;x: intero, S: lista ordinata
    (let((i (+ 1 0)))
    (cond ((null? S) "elemento non trovato")
          ((= (car S) x) (- i 1))  ;(length S)
          (else
           (+ i (position x (cdr S))))
          ))
    ))
;(position 7 '(7 8 24 35 41))           

;restituisce la lista S con l'aggiunta dell'elemento x
(define sorted-ins   ;val: lista
 (lambda (x S)    ;x: intero, S: lista ordinata
   (cond ((null? S) (cons x null))
         ((> x (car S)) (cons (car S) (sorted-ins x (cdr S))))
         ((< x (car S)) (cons x S)) 
         (else S)                   ;elemento già presente nella lista
         ) 
   ))
 ;(sorted-ins 24 '())

;restituisce la lista S ordinata
(define sorted-list  ;val: lista ordinata
  (lambda (S)        ;S: lista disordinata  
      (cond ((null? S) S)
            (else  (sorted-ins  (car S) (sorted-list (cdr S))))
              )  
    )) 
;(sorted-list '(35 8 41 24 7))
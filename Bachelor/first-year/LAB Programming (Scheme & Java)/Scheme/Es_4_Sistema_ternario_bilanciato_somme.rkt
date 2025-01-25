;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Es4 Sistema ternario bilanciato somme|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;; Somma di tre cifre nel sistema ternario bilanciato (caratteri):
;; - u, v rappresentano le cifre "incolonnate",
;; - c rappresenta il riporto "in entrata";
;; - la cifra restituita rappresenta la cifra "incolonnata"
;;   con u, v nel risultato.
;;
;; Il riporto va considerato a parte,
;; definendo una procedura "carry" con analoga struttura per casi.


(define btr-digit-sum                    ; val:     carattere +/./-
  (lambda (u v c)                        ; u, v, c: caratteri +/./-
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\.)
                        ((char=? c #\.)  ; - - .
                         #\+)
                        ((char=? c #\+)  ; - - +
                         #\-)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\+)
                        ((char=? c #\.)  ; - . .
                         #\-)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  c)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\+)
                        ((char=? c #\.)  ; . - .
                         #\-)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  c)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\+)
                        ((char=? c #\+)  ; . + +
                         #\-)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  c)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\+)
                        ((char=? c #\+)  ; + . +
                         #\-)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\+)
                        ((char=? c #\.)  ; + + .
                         #\-)
                        ((char=? c #\+)  ; + + +
                         #\.)))))
          )))

;rimuove i caratteri \#. ininfluenti 
(define normalized-btr   ;val: stringa
  (lambda (num)          ;num: stringa
    (cond((string=? (substring num 0) ".")
          ".")
         ((char=? (string-ref num 0) #\.)
          (normalized-btr (substring num 1)))
         (else
          num)
       )
    ))

;restituisce il carattere meno significativo
(define lsd      ;val: carattere
  (lambda (num)  ;num: stringa
    (let((k (string-length num)))
      (if (string=? (substring num 0 k) "")
          #\.
          (string-ref num (- k 1)) ;(substring num (- k 1))
          )
      )
    ))

;restituisce stringa con senza il carattere meno significativo
(define head     ;val: stringa
  (lambda (num)  ;num: stringa
    (let((k (string-length num)))
      (if (string=? (substring num 0 k) "")
          ""
          (substring num 0 (- k 1))
          )
      )
    ))

;restituisce il carry della somma
(define btr-carry                        ; val:     carattere +/./-
  (lambda (u v c)                        ; u, v, c: caratteri +/./-
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-) 
                  (cond ((char=? c #\-)  ; - - -
                         #\-)
                        ((char=? c #\.)  ; - - .
                         #\-)
                        ((char=? c #\+)  ; - - +
                         #\.))) 
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\-)
                        ((char=? c #\.)  ; - . .
                         #\.)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  #\.)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\-)
                        ((char=? c #\.)  ; . - .
                         #\.)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  #\.)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\.)
                        ((char=? c #\+)  ; . + +
                         #\+)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  #\.) 
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\.)
                        ((char=? c #\+)  ; + . +
                         #\+)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\.)
                        ((char=? c #\.)  ; + + .
                         #\+)
                        ((char=? c #\+)  ; + + +
                         #\+)))))
          )))
;(btr-carry #\- #\- #\-)

;somma di due stringhe di caratteri (con il riporto incluso nella somma)
(define btr-carry-sum    ;val: stringa
 (lambda (u v c)         ;u, v: stringhe c: carattere
   (if(and(string=? u "") (string=? v ""))
      ;(string-append 
       ;(string (btr-carry (lsd u) (lsd v) c))
      (string (btr-digit-sum (lsd u) (lsd v) c));)
      (string-append  (btr-carry-sum (head u) (head v) (btr-carry (lsd u) (lsd v) c))
                      (string (btr-digit-sum (lsd u) (lsd v) c)))
      )
   ))

; (btr-carry-sum "+" "-" #\.)

;somma delle stringhe
(define btr-sum  ;val:  stringa
  (lambda (u v)  ;u, v: stringhe
       ;(if(and(string=? u "") (string=? v ""))
       (normalized-btr (btr-carry-sum  u  v #\.))    
       ))
;(btr-sum "-+--" "+")
 
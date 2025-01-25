;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Es_8_Hanoi) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks")) #f)))
(define hanoi-disks     ; val: lista di coppie
  (lambda (n k)            ; n > 0 intero
    (result n k 1 2 3 0 0 0)
   ))
;(asticelle 3 1)

;restituisce la configurazione al termina della k-ima mossa
(define result                ;val: lista di coppie
 (lambda (n k s d t ns nd nt)         ;n: dischi (n>0), k: mossa (0<=k<=2^n-1)
   (cond ((= n 0) 
          (list (list s ns) (list d nd) (list t nt))) 
         ((<= (expt 2 (- n 1)) k) ;siamo nel primo caso dove il blocco grande non viene mosso (meno di 2^n-1) -> per questo motivo varia il numero di blocchi e non direttamente quello delle mosse
          (result (- n 1) (- k (expt 2 (- n 1))) t d s nt (+ nd 1) ns))
         (else ;abbiamo mosso il blocco grande
          (result (- n 1) k s t d (+ ns 1) nt nd)) ;s e t si scambiano perchè cambia il valore della nostra surce (pilastro dove si strovano tutti i blocchi tranne l'utimo) 
         )
   ))

(define hanoi-picture     ; val: lista di coppie
  (lambda (n k)            ; n: dischi (n>0), k: mossa (0<=k<=2^n-1)
    (render n k 1 2 3 0 0 0 (towers-background n) n) ; l'iltimo 1 rappresenta lasta che stiamo considerando
   ))


;restituisce la configurazione al termina della k-ima mossa
;(define render                ;val: lista di coppie
; (lambda (l n s)         ;l: lista di coppie ;n: numero di disch ; s:staff
;   (if (< 4 s) ;stampo solo fino alla terza asta
;   (s)
;   (above (render (cdr l) n (+ s 1))(above (disk-image 4 n s 0) (towers-background n)))
;   ))

(define render                ;val: lista di coppie
 (lambda (n k s d t ns nd nt imm h)         ;n: dischi (n>0), k: mossa (0<=k<=2^n-1) ; h: altezza
   (cond ((= n 0) imm)
         ((<= (expt 2 (- n 1)) k) (render (- n 1) (- k (expt 2 (- n 1))) t d s nt (+ nd 1) ns (above (disk-image n h d nd) imm) h))
         (else (render (- n 1) k s t d (+ ns 1) nt nd (above (disk-image n h s ns) imm) h))
         )
   ))




  
;(disk-image 3 5 3 1)
;primo parametro indica la grandezza del disco
;il secondo è l'altezza staffa
;il terzo è l'i-esima asta
;ultimo è l'altezza a cui è posto
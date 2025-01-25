;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Manhattan 3D|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define man-3d     ; val: intero
  (lambda (i j k)    ; i, j, k interi non negativi
       (cond((and (= i 0)(= j 0)(= k 0)) 0) 
            ((and(= k 0)(> i 0)(> j 0)) (+ (man-3d i (- j 1) k) (man-3d (- i 1) j k)))
            ((and(= i 0)(> j 0)(> k 0)) (+ (man-3d i (- j 1) k) (man-3d i j (- k 1))))
            ((and(= j 0)(> i 0)(> k 0)) (+ (man-3d (- i 1) j k) (man-3d i j (- k 1))))
            ((and(= i 0)(= j 0)) 1)
            ((and(= i 0)(= k 0)) 1)
            ((and(= j 0)(= k 0)) 1)
         (else
          (+ (man-3d i (- j 1) k)
             (man-3d (- i 1) j k)
             (man-3d i j (- k 1))
             )            
          ))
  ))
;(man-3d 0 0 7)
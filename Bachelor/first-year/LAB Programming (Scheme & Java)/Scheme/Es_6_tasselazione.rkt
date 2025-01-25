;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Es_6_tasselazione) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;(set-tasselation-shift-step!)

(define L-tessellation
  (lambda (l) 
      (if(= l 1)
         L-tile
         (glue-tiles
          (glue-tiles
           (L-tessellation (/ l 2))
           (shift-right (shift-down( L-tessellation(/ l 2)) (/ l 2))(/ l 2)))
           (glue-tiles
           (shift-right (quarter-turn-right ( L-tessellation(/ l 2)))l)
           (shift-down (quarter-turn-left ( L-tessellation(/ l 2)) )l)
           )
           )
          )
         )
    )
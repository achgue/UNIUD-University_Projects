;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Es_2_Croce_Quadrato) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;;   larger-tile
;;
;;   smaller-tile

;;   (shift-down <forma> <passi>)
;;
;;   (shift-right <forma> <passi>)
;;
;;   (quarter-turn-right <forma>)
;;
;;   (quarter-turn-left <forma>)
;;
;;   (half-turn <forma>)
;;
;;   (glue-tiles <forma> <forma>)
;;   (set-puzzle-shift-step!)

(set-puzzle-shift-step!)

;Croce
(glue-tiles
  (glue-tiles
   (shift-down (quarter-turn-left smaller-tile) 2)
   (shift-down (quarter-turn-left larger-tile) 2))
  (glue-tiles
   (shift-right (shift-down (quarter-turn-right smaller-tile) 2) 5)
   (shift-right (quarter-turn-right larger-tile) 1))
  )

;Quadrato
(shift-down
 (glue-tiles
  (glue-tiles
    (shift-right(half-turn smaller-tile) 2)
    (half-turn larger-tile))
  (glue-tiles
   (shift-right (shift-down  smaller-tile 5) 2)
   (shift-down (shift-right larger-tile 2) 1))
  ) 1)
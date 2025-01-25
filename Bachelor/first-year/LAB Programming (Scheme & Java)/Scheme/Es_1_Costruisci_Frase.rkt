;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Frase giusta|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define frase
  (lambda (sog ver ogg)
    (if(plurale? sog)
      (if (femminile? sog)
        (if(plurale? ogg)
          (if (femminile? ogg)    
              (plurale-sfopf sog ver ogg)   ;
              (plurale-sfopm sog ver ogg))  ;
          (if (femminile? ogg)    
              (plurale-sfosf sog ver ogg)   ;
              (plurale-sfosm sog ver ogg))  ;
          ) 
        (if(plurale? ogg)
          (if (femminile? ogg)    
              (plurale-smopf sog ver ogg)   ;
              (plurale-smopm sog ver ogg))  ;
          (if (femminile? ogg)    
              (plurale-smosf sog ver ogg)   ;
              (plurale-smosm sog ver ogg))  ;
          )
        )
      (if (femminile? sog)
        (if(plurale? ogg)
          (if (femminile? ogg)    
              (singolare-sfopf sog ver ogg)  ;
              (singolare-sfopm sog ver ogg)) ;
          (if (femminile? ogg)    
              (singolare-sfosf sog ver ogg)  ;
              (singolare-sfosm sog ver ogg)) ;
          )
        (if(plurale? ogg)
          (if (femminile? ogg)    
              (singolare-smopf sog ver ogg)  ;
              (singolare-smopm sog ver ogg)) ;
          (if (femminile? ogg)    
              (singolare-smosf sog ver ogg)  ;
              (singolare-smosm sog ver ogg)) ;
          )
        )
)))

      
       
          
  

(define plurale?
  (lambda (sog)
    (or(string=? (substring sog (-(string-length sog)1)) "e")
       (string=? (substring sog (-(string-length sog)1)) "i"))
    
    )
  )
(define femminile? 
  (lambda(sog)
    (or(string=? (substring sog (-(string-length sog)1)) "a")
       (string=? (substring sog (-(string-length sog)1)) "e"))
    ;(char? (string-ref sog (-(string-length sog)1)) #\a)
      )
    )

(define plurale-smosf
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "are")
        (string-append "le " sog " "(substring ver 0 (-(string-length ver) 3))"ano" " la " ogg)
        (string-append "le " sog " "(substring ver 0 (-(string-length ver) 3))"ono" " la " ogg)
        )
                ;(string-append (substring sog 0 (-(string-length sog)1))"e")
   ))

(define plurale-sfosm  ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "are")
        (string-append "le " sog " "(substring ver 0 (-(string-length ver) 3))"ano" " il " ogg)
        (string-append "le " sog " "(substring ver 0 (-(string-length ver) 3))"ono" " il " ogg)
        )
                ;(string-append (substring sog 0 (-(string-length sog)1))"e")
   ))
(define plurale-sfosf ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "are")
        (string-append "le " sog " "(substring ver 0 (-(string-length ver) 3))"ano" " la " ogg)
        (string-append "le " sog " "(substring ver 0 (-(string-length ver) 3))"ono" " la " ogg)
        )
                ;(string-append (substring sog 0 (-(string-length sog)1))"e")
   ))
(define singolare-smosm ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "ire")
             (string-append "il " sog " "(substring ver 0 (-(string-length ver) 3))"e" " il " ogg)
             (string-append "il " sog " "(substring ver 0 (-(string-length ver) 2))" " "il " ogg)
              )
    ; (string-append (substring sog 0 (-(string-length sog)1))"i")
   ))

(define singolare-smosf ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "ire")
             (string-append "il " sog " "(substring ver 0 (-(string-length ver) 3))"e" " la " ogg)
             (string-append "il " sog " "(substring ver 0 (-(string-length ver) 2))" " "la " ogg)
              )
    ; (string-append (substring sog 0 (-(string-length sog)1))"i")
   ))
(define singolare-sfosm ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "ire")
             (string-append "la " sog " "(substring ver 0 (-(string-length ver) 3))"e" " il " ogg)
             (string-append "la " sog " "(substring ver 0 (-(string-length ver) 2))" " "il " ogg)
              )
                 ;(string-append (substring sog 0 (-(string-length sog)1))"e")
   ))





(define plurale-smopm  ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "are")
        (string-append "i " sog " "(substring ver 0 (-(string-length ver) 3))"ano" " i " ogg)
        (string-append "i " sog " "(substring ver 0 (-(string-length ver) 3))"ono" " i " ogg)
        )
         ; (string-append (substring sog 0 (-(string-length sog)1))"i")
      
   ))

(define plurale-sfopm  ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "are")
        (string-append "le " sog " "(substring ver 0 (-(string-length ver) 3))"ano" " i " ogg)
        (string-append "le " sog " "(substring ver 0 (-(string-length ver) 3))"ono" " i " ogg)
        )
                ;(string-append (substring sog 0 (-(string-length sog)1))"e")
   ))
(define singolare-smopm ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "ire")
             (string-append "il " sog " "(substring ver 0 (-(string-length ver) 3))"e" " i " ogg)
             (string-append "il " sog " "(substring ver 0 (-(string-length ver) 2))" " "i " ogg)
              )
    ; (string-append (substring sog 0 (-(string-length sog)1))"i")
   ))

(define singolare-sfopm  ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "ire")
             (string-append "la " sog " "(substring ver 0 (-(string-length ver) 3))"e" " i " ogg)
             (string-append "la " sog " "(substring ver 0 (-(string-length ver) 2))" " "i " ogg)
              )
                 ;(string-append (substring sog 0 (-(string-length sog)1))"e")
   ))






  (define plurale-smosm ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "are")
        (string-append "i " sog " "(substring ver 0 (-(string-length ver) 3))"ano" " il " ogg)
        (string-append "i " sog " "(substring ver 0 (-(string-length ver) 3))"ono" " il " ogg)
        )
         ; (string-append (substring sog 0 (-(string-length sog)1))"i")
      
   ))






(define plurale-smopf ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "are")
        (string-append "i " sog " "(substring ver 0 (-(string-length ver) 3))"ano" " le " ogg)
        (string-append "i " sog " "(substring ver 0 (-(string-length ver) 3))"ono" " le " ogg)
        )
         ; (string-append (substring sog 0 (-(string-length sog)1))"i")
      
   ))

(define plurale-sfopf ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "are")
        (string-append "le " sog " "(substring ver 0 (-(string-length ver) 3))"ano" " le " ogg)
        (string-append "le " sog " "(substring ver 0 (-(string-length ver) 3))"ono" " le " ogg)
        )
                ;(string-append (substring sog 0 (-(string-length sog)1))"e")
   ))

(define singolare-sfopf ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "are")
        (string-append "la " sog " "(substring ver 0 (-(string-length ver) 3))"ano" " le " ogg)
        (string-append "la " sog " "(substring ver 0 (-(string-length ver) 3))"ono" " le " ogg)
        )
                ;(string-append (substring sog 0 (-(string-length sog)1))"e")
   ))

(define singolare-smopf ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "ire")
             (string-append "il " sog " "(substring ver 0 (-(string-length ver) 3))"e" " le " ogg)
             (string-append "il " sog " "(substring ver 0 (-(string-length ver) 2))" " "le " ogg)
              )
    ; (string-append (substring sog 0 (-(string-length sog)1))"i")
   ))



(define singolare-sfosf ;
 (lambda (sog ver ogg)
   (if(string=? (substring ver (-(string-length ver) 3)) "ire")
             (string-append "la " sog " "(substring ver 0 (-(string-length ver) 3))"e" " la " ogg)
             (string-append "la " sog " "(substring ver 0 (-(string-length ver) 2))" " "la " ogg)
              )
                 ;(string-append (substring sog 0 (-(string-length sog)1))"e")
   ))
;(frase "gatto" "cacciare" "topi")
(+ 1 2)
(+ (* 8 7) (- 1 2))
(* (+ 8 7) (- 1 2))
(+ (+ 8 7) (+ 1 2))

(/ 3 4)
(/ 4 3)
(/ 4 8)
(/ 8 4)
; give warning
(/ 4 0)
(/ 4 (- 9 (+ 8 1)))
(/ (/ 4 (- 9 (+ 8 1))) 2)
(/ 10000000 (/ 4 (- 9 (+ 8 1))))
(- (/ 4 (- 9 (+ 8 1))) (/ 4 (- 9 (+ 8 1))))

(* 5 6)

; OK in LISP, not LL
(+)
(+ 3 4 5)
(*)
(* 8 9 7 6)
(- 1)
(/ 2)
(- 2 3 4)
(/ 3 4 5)

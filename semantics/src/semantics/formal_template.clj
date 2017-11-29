(ns semantics.formal-template)

;; AXIOMATIC semantics
(let [x 41
      y (+ x 1)
      z y] z)

;; OPERATIONAL semantics

(let [x 3
      and! and] ;; you should really use a function :)
  (+ (* 1 (and! true 40))
     (if x 2 nil)))

;; DENOTATIONAL semantics

(let [s "Not here my friend!"] (prn "Where is denotational semantics in Clojure?" s))

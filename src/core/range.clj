(ns core.range
  (:require [clojure.test :refer [is]]))
;; range function without arguments returns
;; an infinite lazy sequence of numbers.
(is (= '(0 1 2 3 4) (take 5 (range))))
;; We can specifyt the start value for
;; a lazy infinite sequence of numbers.
(is (= '(0 1 2 3 4) (range 5)))
;; With the second argument we set the
;; end value for our lazy sequence of numbers.
;; The end value is exclusive for the range.
(is (= '(5 6 7 8 9) (range 5 10)))
;; The third argument defines the step value
;; between numbers, which by default is 1.
(is (= '(0 2 4 6 8) (range 0 10 2)))
;; We can also have a lazy sequence counting
;; numbers back.
(is (= '(5 4 3 2 1) (range 5 0 -1)))
(is (= '(100 97 94 91 88) (take 5 (range 100 0 -3))))
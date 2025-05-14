(ns core.fnil
  (:require [clojure.test :refer [is]]))
;; + throws NullPointerException when argument is nil.
(is (thrown? NullPointerException (+ nil 1)))
;; Create new function with default 0 when argument is nil.
(is (= 1 ((fnil + 0) nil 1)))

(def times
  "Return function * with
default value 1 for argument 1 when nil,
default value 10 for argumen 2 when nil and
default value 100 for argument 3 when nil."
  (fnil * 1 10 100))
(is (= 2
       (times nil 2)
       (* 1 2)))
(is (= 20
       (times 2 nil)
       (* 2 10)))
(is (= 2000
       (times 2 nil nil)
       (* 2 10 100)))
(is (= 200
       (times nil 2 nil)
       (* 1 2 100)))
(is (= 20
       (times nil nil 2)
       (* 1 10 2)))
;; We can use more arguments that are passed
;; to the original * function.
(is (= 4000
       (times 2 nil nil 2)
       (* 2 10 100 2)))
(is (= 1000
       (times nil nil nil)
       (* 1 10 100)))
(is (= 2000
       (times nil nil nil 2)
       (* 1 10 100 2)))
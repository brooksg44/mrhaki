(ns set.function
  (:require [clojure.test :refer [is]]))

;; Sample set with some JVM languages.
(def languages #{"Clojure" "Groovy" "Kotlin"})
;; We can use the set languages as function
;; with one argument to check if the argument
;; is part of the set.
(is (= "Clojure" (languages "Clojure")))
;; If the argument is not part of the set
;; we get back nil.
(is (= nil (languages "Java")))
;; As nil is logical false in Clojure
;; a set makes a nice predicate.
(is (= ["Clojure"] (filter #{"Clojure" "Java"} languages)))
(is (= ["Kotlin" "Groovy"] (remove #{"Java" "Clojure"} languages)))
;; Sample vector with numbers.
(def numbers [0 2 1 4 2 3 1 0])
;; Use set as predicate.
(is (= [2 1 2 1] (filter #{1 2} numbers)))

;; As set #{1 2} is a function we can use it as argument
;; for other functions to create a new function.
(is (= [0 4 3 0] (filter (complement #{1 2}) numbers)))
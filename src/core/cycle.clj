(ns core.cycle
  (:require [clojure.test :refer [is]]))
;; The items in the collection are repeated
;; and return type is a lazy sequence.
(is (= [0 1 0 1 0 1]
       (take 6 (cycle [0 1]))))
(is (seq? (cycle [0 1])))
(is (= [\C \l \o \j \u \r \e \C \l \o \j \u \r \e]
       (take 14 (cycle "Clojure"))))
;; Useful for functions that want equally sized
;; collection arguments.
(is (= {:a 0 :b 1 :c 0 :d 1}
       (zipmap [:a :b :c :d] (cycle [0 1]))))
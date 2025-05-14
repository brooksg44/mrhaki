(ns core.mapcat

  (:require [clojure.test :refer [is]]))
;; The function argument for mapcat returns a collection
;; with the original element of the collection
;; and the value added by 10.
(is (= [1 11 2 12 3 13]
       (mapcat (fn [n] [n (+ 10 n)]) [1 2 3])))
(is (= [1 1 2 2 3 3]
       (mapcat (partial repeat 2) [1 2 3])
       ;; Using apply concat with map returns the same result.
       (apply concat (map (partial repeat 2) [1 2 3]))))
;; Combined with juxt
(is (= ["mrhaki" 6 "blog" 4]
       (mapcat (juxt identity count) ["mrhaki" "blog"])))
;; Our first example rewritten with juxt.
(is (= [1 11 2 12 3 13]
       (mapcat (juxt identity (partial + 10)) [1 2 3])))
;; We can use multiple collections,
;; the function then accepts multiple arguments.
(is (= [1 100 100 2 200 400 3 300 900]
       (mapcat (fn [a b] [a b (* a b)]) [1 2 3] [100 200 300]))

    (comment
      (mapcat (fn [a b] [a b (+ a b)]) [1 2 3] [100 200 300])
      ;;=> (1 100 101 2 200 202 3 300 303) 
      (mapcat (fn [a b] [a b (* a b)]) [1 2 3] [100 200 300])
      ;;=> (1 100 100 2 200 400 3 300 900)
      ))

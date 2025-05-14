(ns core.map-indexed
  (:require [clojure.test :refer [is]]))
;; map-indexed applies a function to each element
;; in a collection where the function gets the
;; index of the item in the collection and the item itself.
(is (= [[0 3] [1 20] [2 10] [3 2] [4 1]]
       (map-indexed (fn [index number] [index number]) [3 20 10 2 1])))

(defn indices
  "Return lazy sequence of indices of elements in a collection."
  [coll]
  (map-indexed (fn [index _] index) coll))

(is (= [0 1 2 3 4] (indices [3 20 10 2 1])))

(defn char-range
  "Function to return a range of characters from `start` to `end` (including)."
  [start end]
  (map char (range (int start) (inc (int end)))))

(def a-z (char-range \a \z)) ;; characters from a to z.

;; map-indexed returns a lazy sequence.
(is (= [[\a 0] [\b 1] [\c 2]]
       (take 3 (map-indexed (fn [index ch] [ch index]) a-z))))

;; Create map with letter keys and position in alphabet as values.
(def letters-positions (into {} (map-indexed (fn [index ch] [ch (inc index)]) a-z)))

(is (= [[\a 1] [\b 2] [\c 3]]
       (take 3 letters-positions)))

;; Find position of each letter of word "clojure".
(is (= [3 12 15 10 21 18 5]
       (reduce (fn [result value] (conj result (get letters-positions value)))
               []
               "clojure"))
    (comment
      (map-indexed (fn [index number] (* index number)) [1 1 1 1 1]))
    ;;=> (0 1 2 3 4)
    )
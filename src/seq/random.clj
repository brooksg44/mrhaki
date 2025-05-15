(ns seq.random
  (:require [clojure.test :refer [is]]))
;; We use the function rand-nth to get a
;; random element from a sequence collection.
(is (contains? #{"Clojure" "Java" "Groovy"}
               (rand-nth ["Groovy", "Clojure", "Java"])))
;; We can use the rand-nth function with a map
;; if we first turn it into a sequence.
(is (contains? #{[:a 1] [:b 2]} (rand-nth (seq {:a 1 :b 2}))))

;;This next example shows how we can use the random-sample function:
;;(ns mrhaki.seq.random
;;(:require [clojure.test :refer [is]]))

;; Using random-sample each item is in the
;; result based on the random probability of the
;; probability argument.
;; When probability is 1 all items are returned.
(is (= ["Clojure" "Groovy" "Java"]
       (random-sample 1.0 ["Clojure" "Groovy" "Java"])))
;; When proability is 0 no item is in the result.
(is (empty? (random-sample 0 ["Clojure" "Groovy" "Java"])))
;; Any other value between 0 and 1 will return different
;; results for each invocation of the random-sample function.
(def samples (random-sample 0.4 ["Clojure" "Groovy"]))
(is (or (empty? samples)
        (= ["Clojure" "Groovy"] samples)
        (= ["Clojure"] samples)
        (= ["Groovy"] samples)))
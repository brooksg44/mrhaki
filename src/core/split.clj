(ns core.split

  (:require [clojure.test :refer [is]]))
;; The split-with function has a predicate and returns the result
;; of the functions take-while and drop-while in a result vector.
(let [less-than-5? (partial > 5)
      numbers (range 11)]
  (is (= ['(0 1 2 3 4) '(5 6 7 8 9 10)]
         (split-with less-than-5? numbers))
      [(take-while less-than-5? numbers) (drop-while less-than-5? numbers)]))
;; In this example we take while the value is a String value and
;; drop while starting from first value that is not a String.
(letfn [(string-value? [[k v]] (instance? String v))]
  (is (= ['([:language "Clojure"] [:alias "mrhaki"]) '([:age 47] [:country "NL"])]
         (split-with string-value? {:language "Clojure" :alias "mrhaki" :age 47 :country "NL"}))))
;; Instead of splitting on a predicate we can just give the number
;; of elements we want to split on with the split-at function.
(is (= ['(0 1 2 3) '(4 5 6 7)]
       (split-at 4 (range 8))
       [(take 4 (range 8)) (drop 4 (range 8))]))
(is (= ['([:language "Clojure"] [:alias "mrhaki"] [:age 47]) '([:country "NL"])]
       (split-at 3 {:language "Clojure" :alias "mrhaki" :age 47 :country "NL"})))

(comment
  (def less-than-5? (partial > 5))
  ;;=> #'core.split/less-than-5?
  (def numbers (range 11))
  ;;=> #'core.split/numbers
  (split-with less-than-5? numbers)
  ;;=> [(0 1 2 3 4) (5 6 7 8 9 10)]
  [(take-while less-than-5? numbers) (drop-while less-than-5? numbers)]
  ;;=> [(0 1 2 3 4) (5 6 7 8 9 10)]

  (split-at 2 {:language "Clojure" :alias "mrhaki" :age 47 :country "NL"}))
;;=> [([:language "Clojure"] [:alias "mrhaki"]) ([:age 47] [:country "NL"])]
 
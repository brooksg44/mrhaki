(ns string.sample
  (:import [java.util Currency Locale])
  (:require [clojure.string :refer [join]]
            [clojure.test :refer [is]]))
;; Join without explicit separator simply concats values in collection.
(is (= "abc" (join ["a" "b" "c"])))
;; Join with separator uses separator between elements from collection
;; and omits the separator after the last element.
(is (= "a, b, c" (join ", " ["a" "b" "c"])))
;; Join works on multiple collection types,
;; because each collection is transformed to a seq.
(is (= "a::b::c" (join "::" #{"a" "b" "c"})))
;; Collection with non-strings is also returned as string.
;; The string representation of each element is used.
(is (= "0 1 2 3 4 5 6 7 8 9 10" (join " " (range 11))))
(is (= "https://www.mrhaki.com:443/,EUR" (join \, [(java.net.URL. "https" "www.mrhaki.com" 443 "/")
                                                   (Currency/getInstance (Locale. "nl" "NL"))])))
;; Nil values are ignored in the join results,
;; but separator is still used for nil element.
(is (= "Clojure--is cool--!" (join "-" ["Clojure" nil "is cool" nil "!"])))
;; Function query-params to transform a map structure with
;; keyword keys to URL request parameters.
(defn query-params
  "Return key/value pairs as HTTP request parameters separated by &.
Each request parameter name and value is separated by =.
E.g. {:q \"Clojure\" :max 10 :start 0 :format \"xml\"} is transformed
to q=Clojure&max=10&start=0&format=xml."
  [params]
  (let [query-param (fn [[param-name param-value]] (join "=" [(name param-name) param-value]))]
    (join "&" (map query-param params))))

(is (= "q=Clojure&max=10&start=0&format=xml"
       (query-params {:q "Clojure" :max 10 :start 0 :format "xml"})))


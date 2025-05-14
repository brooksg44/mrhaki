(ns string.includes
  (:require [clojure.string :as str]
            [clojure.test :refer [is]]))
;; String to check.
(def s "Clojure is cool!")
;; Check if given value is part of the string.
(is (true? (str/includes? s "cool")))
(is (false? (str/includes? s "boring")))
;; Check string starts with given value.
(is (true? (str/starts-with? s "Clojure")))
(is (false? (str/starts-with? s "Groovy")))
;; Check string ends with given value.
(is (true? (str/ends-with? s "cool!")))
;; Helper function to see if string with regular expression
;; matches a given string value using java.lang.String#matches.
(defn matches?
  "Return true when string `re` with regular expression
   matches for value `s`, false otherwise.
   Another use of ^ is for type hints"
  [^CharSequence s ^CharSequence re]
  (. s matches re))
(is (true? (matches? s ".*is.*")))
(is (false? (matches? s "cool")))
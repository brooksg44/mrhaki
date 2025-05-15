(ns seq
  (:require [clojure.test :refer [is]]))
(def langs [{:language "Clojure" :site "https://clojure.org" :dynamic true}
            {:language "Groovy" :site "https://www.groovy-lang.org" :dynamic true}
            {:language "Java" :site "https://www.java.com" :dynamic false}
            {:language "Kotlin" :site "https://kotlinlang.org" :dynamic false}])
;; Find first map entry of first map in languages.
(is (= [:language "Clojure"]
       (ffirst langs)
       (first (first langs))))
;; Find next map entries for first map in languages.
(is (= (list [:site "https://clojure.org"] [:dynamic true])
       (nfirst langs)
       (next (first langs))))
;; Find first map of next maps in languages.
(is (= {:language "Groovy" :site "https://www.groovy-lang.org" :dynamic true}
       (fnext langs)
       (first (next langs))))
;; Find next maps of next maps in languages.
(is (= (list {:language "Java" :site "https://www.java.com" :dynamic false}
             {:language "Kotlin" :site "https://kotlinlang.org" :dynamic false})
       (nnext langs)
       (next (next langs))))
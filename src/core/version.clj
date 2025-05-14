(ns core.version
  (:require [clojure.test :refer [is]]))

;; Function clojure-version returns Clojure version.
(is (= "1.12.0" (clojure-version)))
(defn java-version
  "Returns Java version as printable string."
  []
  (System/getProperty "java.version"))
(is (= "23.0.2" (java-version)))
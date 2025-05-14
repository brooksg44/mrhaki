(ns string.replace
  (:require [clojure.string :as str]
            [clojure.test :refer [is]]))
;; Example string value to do replacements on.
(def s "Programming with Clojure is fun!")
;; Match argument can be a string value,
;; that gives same result as java.lang.String#replace method.
(is (= "Programming with Clojure is awesome!"
       (str/replace s "fun" "awesome")
       (.replace s "fun" "awesome")))
;; Match argument can also be regular expression pattern.
(is (= "Programming_with_Clojure_is_fun!"
       (str/replace s #"\s+" "_")))
;; When the regular expression pattern has groups
;; we can refer to them using $ followed by matched
;; group number, eg. $1 for the first group.
(is (= "Execution 1 took 200ms"
       (str/replace "run1=200ms" #"run(\d+)=(\d+ms)" "Execution $1 took $2")))
;; Replace argument can be a function.
;; Argument of the function is string of entire match
;; if there are no nested groups.
(is (= "[NOTE] [CAUTION]"
       (str/replace "[note] [caution]" #"\[\w+\]" #(.toUpperCase %))))
;; Otherwise if there are nested groups a vector is
;; used as argument for the replacment function
;; where the first argument is the
;; entire match followed by the nested groups.
(is (= "ABC def"
       (str/replace "abc DEF"
                    #"(\w+)(\s+)(\w+)"
                    #(str (.toUpperCase (% 1)) (% 2) (.toLowerCase (% 3))))))
;; By destructuring the vector argument
;; we can refer to the groups using a name.
(defn replacement
  [[_ execution time]]
  (let [seconds (/ (bigdec time) 1000)]
    (str "Execution " execution " took " seconds " seconds")))
(is (= "Execution 1 took 0.2 seconds"
       (str/replace "run1=200ms" #"run(\d+)=(\d+)ms" replacement)))
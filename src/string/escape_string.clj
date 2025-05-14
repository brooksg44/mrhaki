(ns string.escape-string
  (:require [clojure.string :as str]
            [clojure.test :refer [is]]))
(is (= "I 10v3 C10jur3"
       (str/escape "I love Clojure" {\o 0 \e 3 \l 1})))
(is (= "mrHAKI"
       (str/escape "mrhaki" {\h "H" \a "A" \k "K" \i "I" \x "X"})))
(def html-escaping {(char 60) "&lt;" (char 62) "&gt;" (char 38) "&amp;"})
(is (= "&lt;h1&gt;Clojure &amp; Groovy rocks!&lt;/h1&gt;"
       (str/escape "<h1>Clojure & Groovy rocks!</h1>" html-escaping)))
(is (= "Special chars: \\t \\n"
       (str/escape "Special chars: \t \n" char-escape-string)))
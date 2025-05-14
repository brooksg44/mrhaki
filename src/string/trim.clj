(ns string.trim
  (:require [clojure.test :refer [is]]
            [clojure.string :refer [trim triml trimr trim-newline]]))
;; The trim function removes spaces before and after the string.
(is (= "mrhaki" (trim " mrhaki ")))
;; Tabs are also trimmed.
(is (= "mrhaki" (trim "\t mrhaki ")))
;; Return and/or newline characters are also trimmed.
(is (= "mrhaki" (trim "\tmrhaki \r\n")))
;; Character literals that should be trimmed are trimmed.
(is (= "mrhaki" (trim (str \tab \space " mrhaki " \newline))))
;; The triml function removes spaces before the string (trim left).
(is (= "mrhaki " (triml " mrhaki ")))
(is (= "mrhaki " (triml "\t mrhaki ")))
(is (= "mrhaki " (triml "\nmrhaki ")))
(is (= "mrhaki " (triml (str \return \newline " mrhaki "))))
;; The trimr function removes spaces after the string (trim right).
(is (= " mrhaki" (trimr " mrhaki ")))
(is (= " mrhaki" (trimr " mrhaki\t")))
(is (= " mrhaki" (trimr (str " mrhaki " \newline))))
;; The trim-newline function removes only newline from string.
(is (= "mrhaki " (trim-newline (str "mrhaki " \newline))))
(is (= "mrhaki " (trim-newline (str "mrhaki " \return \newline))))
(is (= "mrhaki " (trim-newline "mrhaki \r\n")))
(is (= "mrhaki " (trim-newline "mrhaki ")))
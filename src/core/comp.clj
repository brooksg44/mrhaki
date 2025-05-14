(ns core.comp
  (:require [clojure.test :refer [is]]
            [clojure.string :as str]))
;; Some functions that work with numbers.
(defn f [x] (+ 11 (- x 90)))
(defn g [x] (* 2 x))
;; Use comp to create a new function based
;; on functions f and g.
;; Function are applied from right to left,
;; so first f and then g.
(is (= 42 ((comp g f) 100)))
;; Notice ordering is important.
;; In this case first g, then f is applied.
(is (= 121 ((comp f g) 100)))
;; User data to work with.
(def user {:name "Hubert" :alias "mrhaki"})
;; Simple function to repeat the value twice.
(def duplicate (partial repeat 2))
;; Compose new function from functions
;; :alias (keyword function),
;; str/capitalize and
;; duplicate.
;; Function are applied from right to left.
(is (= '("Mrhaki" "Mrhaki")
       ((comp duplicate str/capitalize :alias) user)))
;; Other alternatives to chaining functions
(is (= '("Mrhaki" "Mrhaki")
       ;; using an anonymous function
       (#(duplicate (str/capitalize (:alias %1))) user)
       ;; or thread macro
       (-> user :alias str/capitalize duplicate)
       ;; or simply nested functions.
       (duplicate (str/capitalize (:alias user)))))
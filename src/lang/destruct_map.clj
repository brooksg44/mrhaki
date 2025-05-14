(ns lang.destruct-map
  (:require [clojure.test :refer [is]]
            [clojure.walk :refer [keywordize-keys]]))
;; Sample map structure we want to destructure.
(def user {:first-name "Hubert"
           :last-name "Klein Ikkink"
           :alias "mrhaki"})
;; We can define a symbol username that will have the
;; the value of the :alias key of the user map.
(let [{username :alias} user]
  (is (= "mrhaki" username)))
;; When we use a non-existing key the symbol will
;; have a nil value, like the symbol city in the
;; following example.
(let [{username :alias city :city} user]
  (is (nil? city))
  (is (= "mrhaki" username)))
;; We can use :or to define a value when a key
;; is not available in the map.
;; Here we define "Tilburg" as default value if
;; the :city key is missing from the map.
(let [{username :alias city :city :or {city "Tilburg"}} user]
  (is (= "Tilburg" city))
  (is (= "mrhaki" username)))
;; The symbol names must match in the definition
;; for the key value and the :or value.
(let [{username :alias lives-in :city :or {lives-in "Tilburg"}} user]
  (is (= "Tilburg" lives-in))
  (is (= "mrhaki" username)))
;; We can use :as to assign the original map
;; to a symbol, that we can use in the code.
(let [{username :alias :as person} user]
  (is (= "Hubert" (:first-name person)))
  (is (= "Klein Ikkink" (:last-name person)))
  (is (= "mrhaki" username)))
;; If the symbol name matches the key name we
;; can use :keys to define that so we have to type less.
(let [{:keys [alias first-name last-name]} user]
  (is (= "mrhaki" alias))
  (is (= "Hubert" first-name))
  (is (= "Klein Ikkink" last-name)))
;; Combination of destruturing options for a map.
(let [{:keys [first-name last-name city]
       :or {city "Tilburg"}
       :as person} user]
  (is (= "Hubert" first-name))
  (is (= "Klein Ikkink" last-name))
  (is (= "Tilburg" city))
  (is (= "mrhaki" (:alias person))))
;; Use destructuring in a function argument.
(defn who-am-i
  [{:keys [first-name last-name city]
    :or {city "Tilburg"}
    :as person}]
  (str first-name " " last-name ", aka " (:alias person) ", lives in " city))
(is (= "Hubert Klein Ikkink, aka mrhaki, lives in Tilburg"
       (who-am-i user)))
;; Another map with string keys.
(def string-map {"alias" "mrhaki" "city" "Tilburg"})
(let [{username "alias" city "city"} string-map]
  (is (= "mrhaki" username))
  (is (= "Tilburg" city)))
;; We can use :strs instead of :keys for string keys.
(let [{:strs [alias city]} string-map]
  (is (= "mrhaki" alias))
  (is (= "Tilburg" city)))
;; Or convert string keys to keywords.
(let [{:keys [alias city]} (keywordize-keys string-map)]
  (is (= "mrhaki" alias))
  (is (= "Tilburg" city)))
;; For completeness we can destructure symbol keys.
(def sym-map {'alias "mrhaki" 'name "Hubert Klein Ikkink"})
(let [{username 'alias} sym-map]
  (is (= "mrhaki" username)))
;; We can use :str instead of :keys.
(let [{:syms [alias name]} sym-map]
  (is (= "mrhaki" alias))
  (is (= "Hubert Klein Ikkink" name)))

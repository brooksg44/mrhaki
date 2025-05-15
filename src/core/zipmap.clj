(ns core.zipmap
  (:require [clojure.test :refer [is]]))
;; zipmap creates a map with keys from the
;; first collection and values from the second.
(is (= {:name "Hubert" :alias "mrhaki"}
       (zipmap [:name :alias] ["Hubert" "mrhaki"])))
;; If the size of the values collection is smaller
;; than the size of the keys collection, only
;; keys that map to a value end in up
;; in the resulting map.
(is (= {:name "Hubert"}
       (zipmap [:name :alias] ["Hubert"])))

;; If the size of the keys collection is smaller
;; than the size of the value collection, then the
;; returned map only contains keys from the keys
;; collection and some values are ignored.
(is (= {:name "Hubert"}
       (zipmap [:name] ["Hubert" "mrhaki"])))
;; Using a lazy sequence created by the repeat
;; function we can set a default value for all keys.
(is (= {:name "" :alias "" :city ""}
       (zipmap [:name :alias :city] (repeat ""))))
;; If we have keys with the same name the last
;; mapping ends up in the resulting map.
(is (= {:name "mrhaki"}
       (zipmap [:name :name] ["Hubert" "mrhaki"])))
;; Keys for the resulting map don't have to be keywords,
;; but can be any type.
(is (= {"name" "Hubert" "alias" "mrhaki"}
       (zipmap ["name" "alias"] ["Hubert" "mrhaki"])))
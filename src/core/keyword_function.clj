(ns core.keyword-function
  (:require [clojure.test :refer [is]]))
;; Sample map to use in examples.
(def user {:name "Hubert" :alias "mrhaki" :living {:country "Netherlands"}})
;; Keyword is a function with a map argument and
;; returns value for keyword in the map.
(is (= "mrhaki"
       (:alias user)
       ;; We get the same result with get.
       (get user :alias)))
(is (= {:country "Netherlands"} (:living user)))
(is (= "Netherlands"
       (:country (:living user))
       (-> user :living :country)
       ;; We can use get-in to get values from nested maps.
       (get-in user [:living :country])))
;; When keyword is not in the map we get a nil result.
(is (nil? (:city user)))
(is (= "not-found" (or (:city user) "not-found")))
;; Works also for namespaced keywords.
(is (= "mrhaki" (:user/alias {:name/full-name "Hubert" :user/alias "mrhaki"})))
;; Using keyword as function with juxt.
(is (= ["mrhaki" "Hubert"]
       ((juxt :alias :name) user)))
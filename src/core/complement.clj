(ns core.complement
  (:require [clojure.test :refer [is]]))
;; List with some temperatures for dates.
(def weather [{:date #inst "2020-06-05" :temperature 21}
              {:date #inst "2020-06-06" :temperature 17}
              {:date #inst "2020-06-07" :temperature 19}
              {:date #inst "2020-06-08" :temperature 25}
              {:date #inst "2020-06-09" :temperature 26}])
(defn good-weather
  "'Good' weather is when the temperature is between 20 and 30 degrees Celcius."
  [{temp :temperature}]
  (< 20 temp 30))
;; The opposite can easily be turned into a
;; new function with complement.
;; The bad-weather takes the same argument (temperature)
;; and returns false when good-weather would return true
;; and true when good-weather returns false.
(def bad-weather (complement good-weather))
(is (= [{:date #inst "2020-06-05" :temperature 21}
        {:date #inst "2020-06-08" :temperature 25}
        {:date #inst "2020-06-09" :temperature 26}]
       (filter good-weather weather)))
(is (= [{:date #inst "2020-06-06" :temperature 17}
        {:date #inst "2020-06-07" :temperature 19}]
       (filter bad-weather weather)))
;; Filtering on the good-weather and complement bad-weather
;; should return the same number of items as in the original collection.
(is (= 5
       (count weather)
       (count (concat (filter good-weather weather) (filter bad-weather weather)))))
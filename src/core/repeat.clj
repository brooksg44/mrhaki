(ns core.repeat
  (:require [clojure.test :refer [is]])
  (:import (java.time LocalTime)))
;; repeat creates an infinite sequence of the given value.
(is (= ["Clojure" "Clojure" "Clojure"]
       (take 3 (repeat "Clojure"))))
;; We can pass a length argument to restrict the number of
;; times the value is repeated.
(is (= ["rocks" "rocks" "rocks"]
       (repeat 3 "rocks")))
(defn parrot-talk
  [s]
  (repeat 2 s))
(is (= ["Polly wants a cracker" "Polly wants a cracker"]
       (parrot-talk "Polly wants a cracker")))
(defn before?
  "Helper function returns true if t1 is before t2, false otherwise"
  [[^LocalTime t1 ^LocalTime t2]]
  (.isBefore t1 t2))
(defn current-time
  "Return current time"
  []
  (LocalTime/now))
;; repeatedly create an infinite sequence of function invocations.
;; The function must have no arguments.
(is (before? (take 2 (repeatedly current-time))))
(is (before? (repeatedly 2 current-time)))
(defn latest-time
  "Get the 'latest' time from a collection with time values."
  [coll]
  (reduce (fn [latest time] (if (.isAfter latest time) latest time)) coll))
(def current-times (repeatedly 100 current-time))
;; as each time is later than the next time value
;; the following equation must be true.
(is (= (latest-time current-times) (last current-times)))
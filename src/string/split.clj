(ns string.split
  (:require [clojure.string :as str]
            [clojure.test :refer [is are]]))
;; Sample string to split.
(def issue "CLJ-90210: Subject")
;; Split on - and : to get vector with string values.
(is (= ["CLJ" "90210" "Subject"] (str/split issue #"-|: ")))
;; The split function accepts a third argument that is
;; a limit on the number of splits that are returned.
(is (= ["CLJ" "90210" "Subject"]
       (str/split issue #"-|: " 0)
       (str/split issue #"-|: " 3)))
(is (= [issue] (str/split issue #"-|: " 1)))
(is (= ["CLJ" "90210: Subject"] (str/split issue #"-|: " 2)))

;; Multiline sample string to split per line and
;; the split each line.
(def itinerary "loc:LVG time:15h-16h activity:Binge-watching
loc:DNR time:18h-19h activity:Eating
loc:MBR time:23h-7h activity:Sleeping")
;; Using split-line function we get a vector
;; where each line is an element.
;; Then for each line we split on : and \s+ and
;; convert it to a map.
;; E.g. first line is
;; {"loc" "LVG" "time" "15h-16h" "activity" "Binge-watching"}
(def agenda (map #(apply hash-map (str/split % #":|\s+"))
                 (str/split-lines itinerary)))
(is (= "LVG" ((first agenda) "loc")))
(is (= "15h-16h" ((first agenda) "time")))
(is (= "Binge-watching" ((first agenda) "activity")))
(is (= "DNR" ((nth agenda 1) "loc")))
(is (= "18h-19h" ((nth agenda 1) "time")))
(is (= "Eating" ((nth agenda 1) "activity")))
(are [value m key] (= value ((last m) key))
  "MBR" agenda "loc"
  "23h-7h" agenda "time"
  "Sleeping" agenda "activity")

(comment
  (str issue)
  ;;=> "CLJ-90210: Subject"
  (str/split issue #"-|: " -1)
  ;;=> ["CLJ" "90210" "Subject"]
  (str/split issue #"-|: " 0)
  ;;=> ["CLJ" "90210" "Subject"]
  (str/split issue #"-|: " 1)
  ;;=> ["CLJ-90210: Subject"]
  (str/split issue #"-|: " 2)
  ;;=> ["CLJ" "90210: Subject"]
  (str/split issue #"-|: " 3)
  ;;=> ["CLJ" "90210" "Subject"]
  (take 40 agenda)
  ;;=> ({"activity" "Binge-watching", "time" "15h-16h", "loc" "LVG"}
  ;;    {"activity" "Eating", "time" "18h-19h", "loc" "DNR"}
  ;;    {"activity" "Sleeping", "time" "23h-7h", "loc" "MBR"})
  ((first agenda) "loc")
  ;;=> "LVG"
  ((first agenda) "time")
  ;;=> "15h-16h"
  ((first agenda) "activity")
  ;;=> "Binge-watching"
  ((nth agenda 1) "loc")
  ;;=> "DNR"
  ((nth agenda 1) "time")
  ;;=> "18h-19h"
  ((nth agenda 1) "activity")
  ;;=> true

  ((second agenda) "loc")
  ;;=> "DNR"
  ((second agenda) "time")
  ;;=> "18h-19h"

  ((second agenda) "activity")
  ;;=> "Eating"

  ((nth agenda 2) "loc")
  ;;=> "MBR"

  ((nth agenda 2) "time")
  ;;=> "23h-7h"

  ((nth agenda 2) "activity")
  ;;=> "Sleeping"
  )


  
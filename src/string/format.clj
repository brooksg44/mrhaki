(ns string.format
  (:require [clojure.test :refer [is]])
  (:import (java.util Locale)))
;; Create new string with format string as template as first argument.
;; Following arguments are used to replace placeholders in the
;; format string.
;; Clojure will delegate to the java.lang.String#format method and
;; we can use all format string options that are defined for this method.
;; More details about the format string syntax can be found in
;; java.util.Formatter. In a REPL we can find the docs
;; with (javadoc java.util.Formatter).
(is (= "https://www.mrhaki.com/"
       (format "https://%s/" "www.mrhaki.com")))
;; Format string with argument index to refer to one argument twice.
(is (= "clojure CLOJURE"
       (format "%1$s %1$S" "clojure")))
;; Format string to define fixed result lenght of 10 characers
;; with padding to get the given length.
(is (= "   Clojure"
       (format "%10s" "Clojure")))
;; Default Locale is used to determine how locale specific
;; formats are applied. In the following example the default
;; decimal separator is . and group separator is , as specified
;; for the Canadian Locale.
(Locale/setDefault Locale/CANADA)
(is (= "Total: 42,000.00"
       (format "Total: %,.2f", 42000.0)))
(defn format-locale
  "Format a string using String/format with a Locale parameter"
  [locale fmt & args]
  (String/format locale fmt (to-array args)))

;; We can use a different Locale to apply different specific
;; locale formats. In the next example we use the Dutch Locale
;; and the decimal seperator is , and the group separator is ..
(is (= "Totaal: 42.000,00"
       (format-locale (Locale. "nl") "Totaal: %,.2f" 42000.0)))
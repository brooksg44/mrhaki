(defproject mrhaki "0.1.0-SNAPSHOT"
  :description "Clojure Goodness Examples"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.clojure/tools.logging "1.2.4"]
                 [ch.qos.logback/logback-classic "1.4.11"]
                 [org.clojure/data.json "2.4.0"]
                 [clj-time "0.15.2"]]
  :main ^:skip-aot mrhaki.core
  :src "/src"
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:dependencies [[org.clojure/test.check "1.1.1"]
                                  [midje "1.10.9"]]
                   :plugins [[lein-midje "3.2.2"]]}})
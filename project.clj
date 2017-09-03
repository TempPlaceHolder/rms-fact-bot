(defproject rms-fact-bot "0.1.0-SNAPSHOT"
  :description "Quotes random RMS facts"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [environ "1.0.0"]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.3.1"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "rms-fact-bot.jar"
  :profile {:production {:env {:production true}}})

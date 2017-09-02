(ns rms-fact-bot.core
  (:gen-class))

(def facts ["rms is god"])

(defn random-fact []
  (rand-nth facts))


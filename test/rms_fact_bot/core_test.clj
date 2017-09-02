(ns rms-fact-bot.core-test
  (:require [clojure.test :refer :all]
            [rms-fact-bot.core :refer :all]))

(deftest returns-a-string
  (testing "Return value is a string"
    (is (string? (random-fact)))))

(deftest returns-a-fact
  (testing "Returns a fact"
    (is (= "rms is god" (random-fact)))))

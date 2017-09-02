(ns rms-fact-bot.core-test
  (:require [clojure.test :refer :all]
            [rms-fact-bot.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(deftest returns-a-string
  (testing "Returns a random string"
    (is (string? random-fact))

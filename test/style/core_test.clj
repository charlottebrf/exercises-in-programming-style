(ns style.core-test
  (:require [clojure.test :refer :all]
            [style.core :refer :all]
            [clojure.string :as str]))

(deftest style-test

  (testing "Will conform an output to lower case"
    (let [sentence "I knew you Were trouble When you Walked In"
          result (lower-case-words sentence)
          expected-result ["i" "knew" "you" "were" "trouble" "when" "you" "walked" "in"]]
      (is (= result expected-result))))

  (testing "Will select the unique words"
    (let [sentence "I knew you Were trouble When you Walked In"
          decapitalised (lower-case-words sentence)
          result (unique-words decapitalised)
          expected-result #{"i" "knew" "you" "were" "trouble" "when" "walked" "in"}]
      (is (= result expected-result))))

  (testing "Will return a map of the words and counts as keys"
    (let [sentence "I am am you you you"
          decapitalised (lower-case-words sentence)
          result (unique-words decapitalised)
          result (map-words-and-count result sentence)
          expected-result '({:word "i" :count 1} {:word "am" :count 2} {:word "you" :count 3})]
      (is (= (sort-by :count result) (sort-by :count expected-result)))))

  (testing "Given a collection of maps will format its unique words & count"
    (let [mapped-sentence '({:word "i" :count 1} {:word "am" :count 2} {:word "you" :count 3})
          result (format mapped-sentence)
          expected-result '("i - 1\n" "am - 2\n" "you - 3\n")]
      (is (= result expected-result))))

  (testing "Given a string will strings of its unique words & count"
    (let [sentence "I am am you you you"
          result (frequency-task sentence)
          expected-result '("am - 2\n" "i - 1\n" "you - 3\n")]
      (is (= result expected-result)))))

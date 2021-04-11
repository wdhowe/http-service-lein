(ns http-service-lein.config-test
  (:require [clojure.test :refer [deftest is testing]]
            [http-service-lein.config :as cfg]))

(deftest conf-test
  (testing "Main config."
    (is (contains? cfg/conf :http-port))
    (is (int? (:http-port cfg/conf)))))

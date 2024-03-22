(ns http-service-lein.default-test
  (:require [clojure.string :as string]
            [clojure.test :refer [deftest is testing]]
            [http-service-lein.default :as default]))

(deftest healthy-test
  (testing "Healthy response."
    (let [resp (default/healthy {:request-method :get
                                 :uri "/healthy"})]
      (is (= 200 (:status resp)))
      (is (get-in resp [:body :healthy])))))

(deftest help-test
  (testing "Help response."
    (let [resp (default/help {:request-method :get
                              :uri "/help"})]
      (is (= 200 (:status resp)))
      (is (string/starts-with? (:body resp) "HTTP API")))))

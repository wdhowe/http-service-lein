(ns http-service-lein.core-test
  (:require [clojure.string :as string]
            [clojure.test :refer [deftest is testing]]
            [http-service-lein.core :as core]
            [ring.mock.request :as mock]))

(deftest app-route-healthy-test
  (testing "app-route '/healthy'"
    (let [response (core/app (mock/request :get "/healthy"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"healthy\":true}")))))

(deftest app-route-help-test
  (testing "app-route '/help'"
    (let [response (core/app (mock/request :get "/help"))]
      (is (= (:status response) 200))
      (is (= true (string/starts-with? (:body response) "HTTP API"))))))

(deftest app-route-invalid-test
  (testing "route '/invalid'"
    (let [response (core/app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

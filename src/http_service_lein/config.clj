(ns http-service-lein.config
  (:gen-class)
  (:require [environ.core :as environ]))

(def conf
  "Load server configuration from the environment."
  {:http-port (Integer. (get environ/env :http-port 8080))})

(comment (println "Config is:\n" conf))

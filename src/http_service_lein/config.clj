(ns http-service-lein.config
  (:gen-class)
  (:require [environ.core :as environ]))

(def conf
  "Load server configuration from the environment."
  {:http-port (Integer. (or (:http-port environ/env) 8080))})

(comment (println "Config is:\n" conf))

(ns http-service-lein.config
  (:require [environ.core :as environ])
  (:gen-class))

(def conf
  "Load server configuration from the environment."
  {:http-port (Integer. (or (:http-port environ/env) 8080))})

(comment (println "Config is:\n" conf))

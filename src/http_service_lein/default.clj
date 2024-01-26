(ns http-service-lein.default
  (:gen-class)
  (:require [clojure.tools.logging :as log]
            [http-service-lein.config :as cfg]
            [trptcolin.versioneer.core :as version]))

(def version "Project version."
  (version/get-version "http-service-lein" "http-service-lein"))

(comment (println "Version is:" version))

(defn config
  "Return the service config settings."
  [request]
  (log/debug (:request-method request) (:uri request))
  {:status 200
   :body cfg/conf})

(comment (config {:request-method :get :uri "/config"}))

(defn healthy
  "Return an ok status."
  [request]
  (log/debug (:request-method request) (:uri request))
  {:status 200 :body {:healthy true}})

(comment (healthy {:request-method :get :uri "/healthy"}))

(defn help
  "Return a help response."
  [request]
  (log/debug (:request-method request) (:uri request))
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (format "HTTP API (%s). Send requests to:\nGET /help -> This help dialog.\nGET /config -> Config settings.\nGET /healthy -> Application health check."
                 version)})

(comment (help {:request-method :get :uri "/help"}))

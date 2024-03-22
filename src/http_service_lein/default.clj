(ns http-service-lein.default
  (:gen-class)
  (:require [clojure.tools.logging :as log]
            [trptcolin.versioneer.core :as version]))

(def version "Project version."
  (version/get-version "http-service-lein" "http-service-lein"))

(comment (println "Version is:" version))

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
   :body (format "HTTP API (%s). Send requests to:\nGET /help -> This help dialog.\nGET /healthy -> Application health check."
                 version)})

(comment (help {:request-method :get :uri "/help"}))

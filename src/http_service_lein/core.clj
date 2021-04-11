(ns http-service-lein.core
  (:require [clojure.tools.logging :as log]
            [compojure.core :as compojure]
            [compojure.route :as route]
            [http-service-lein.config :as cfg]
            [http-service-lein.default :as default]
            [org.httpkit.server :as http-server]
            [ring.middleware.defaults :as ring-defaults]
            [ring.middleware.json :as ring-json])
  (:gen-class))

(compojure/defroutes app-routes
  "HTTP paths are defined here."
  (compojure/context "/" request
    (compojure/GET "/config" [] (default/config request))
    (compojure/GET "/healthy" [] (default/healthy request))
    (compojure/GET "/help" [] (default/help request))
    (route/not-found (format "Path not found: %s" (:uri request)))))

(def app
  "Application routes processed by middleware."
  (-> app-routes
      (ring-json/wrap-json-body {:keywords? true})
      (ring-json/wrap-json-response)
      (ring-defaults/wrap-defaults ring-defaults/api-defaults)))

(defn -main
  "Run the http-server."
  []
  (log/info (format "Starting http-server on port %d." (:http-port cfg/conf)))
  (if (http-server/run-server app {:port (:http-port cfg/conf)})
    (log/info "Http-server started.")
    (log/error "Problem starting http-server.")))

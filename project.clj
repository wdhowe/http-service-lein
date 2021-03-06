(defproject http-service-lein "0.1.0"
  :description "Http service template with Leiningen."
  :url "https://github.com/wdhowe/http-service-lein"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/tools.logging "1.1.0"]
                 [compojure "1.6.2"]
                 [environ "1.2.0"]
                 [http-kit "2.5.3"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.5.1"]
                 [trptcolin/versioneer "0.2.0"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler http-service-lein.core/app}
  :main ^:skip-aot http-service-lein.core
  :target-path "target/%s"
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.4.0"]]}
             :uberjar {:aot :all}})

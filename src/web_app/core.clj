(ns web-app.core
  ;; lang
  (:require [clojure.spec.alpha :as spec])
  (:require [clojure.tools.trace :as trace :refer [deftrace]])
  ;; web
  (:require [ring.adapter.jetty :as adapter])
  (:require [ring.middleware.reload :as reload])
  ;; log
  (:require [taoensso.timbre :as timbre])
  ;; self
  (:require [web-app.router :as router])
  (:gen-class))

(def default-port 3000)
(def default-args {:port default-port})

(defn parse-args
  [args]
  (let [args (first args)]
    (merge default-args args)))

(defn launch-server [options]
  (let [dev-mode? (:dev-mode? options)]
    ;; Launch a Jetty server
    (adapter/run-jetty

      ;; Compojure route handler
      (if dev-mode?
        (reload/wrap-reload #'router/app-router)
        router/app-router)

      ;; Options
      options)))

(defn -main
  [& args] 
  (let [args (parse-args args)
        dev-mode? (:dev-mode args)]

    (timbre/info "Starting server on port" (:port args))
    (if dev-mode?
      (timbre/info "Development mode enabled")
      (timbre/info "Production mode enabled"))

    (launch-server {:port (:port args)
                    :dev-mode? dev-mode?
                    :join? false})))

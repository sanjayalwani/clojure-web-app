(ns web-app.routes
  (:require [ring.util.response :refer [resource-response]])
  (:require [ring.middleware.resource :refer [wrap-resource]])
  (:require [ring.middleware.params :refer [wrap-params]])
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [compojure.handler :as handler]))

(declare not-found)
(declare hello)
(declare reflect)

(defroutes ring-app
  (GET "/hello" req (hello req))
  (GET "/reflect" req (reflect req))
  (GET "/" request
       {:status 200
        :body (slurp "resources/public/index.html")})
  (route/not-found "Not Found"))

(def app (-> ring-app
             wrap-params
             handler/site))

(defn not-found [{:keys [uri]}]
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body (str "Not Found: " uri)})

(defn hello [_]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "Hello, World!")})

(defn reflect [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str req)})

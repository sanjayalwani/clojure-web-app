(ns web-app.router
  (:require [taoensso.timbre :as timbre])
  (:require [ring.util.response :refer [resource-response]])
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route])

  (:require [web-app.pages :as pages]))

(defroutes ring-app
  (GET "/hello" req (pages/hello req))
  (GET "/reflect" req (pages/reflect req))
  (GET "/" _ (timbre/spy (resource-response "public/index.html")))
  
  ;; Serve all static resources from the public directory
  (route/resources "/" {:root "public"})

  ;; Catch all route
  (route/not-found pages/not-found))

(def app-router (-> ring-app
             (wrap-defaults site-defaults)))

(ns web-app.router
  (:require [taoensso.timbre :as timbre])
  (:require [ring.util.response :refer [resource-response content-type response status]] 
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]) 
  (:require [compojure.core :refer [defroutes GET POST routes context]]
            [compojure.route :as route])
  (:require [web-app.pages.todo :as pages.todo]
            [web-app.pages.common :as pages.common]
            [web-app.api.todo :as api.todo]))


(defn json-content-only-guardian [handler]
  (fn [req]
    (if (= "application/json" (:content-type req))
      (handler req)
      (-> {:error "Only JSON requests are allowed"} response (status 403)))))


(defroutes todo-api 
  (context "/api/v1" []
    (json-content-only-guardian
     (POST "/todo/add" req (api.todo/add-todo req)))
    (GET "/todos" req (api.todo/get-todos req))
    (GET "/health" _ (response {:status "ok"}))))


(defroutes website
  (GET "/hello" req (pages.common/hello req))
  (GET "/reflect" req (pages.common/reflect req)) 
  (GET "/todos" req (pages.todo/todo-list req))
  (GET "/todo-form" req (pages.todo/todo-form req))
  (GET "/" _ (content-type (resource-response "public/index.html") "text/html")))


(defroutes fallback-route
  ;; Serve all static resources from the public directory
  (route/resources "/" {:root "public"})
  ;; Catch all route 
  (route/not-found pages.common/not-found))


(def app-routes (routes
                 todo-api 
                 website 
                 fallback-route))

(def app-router (-> app-routes
                    (wrap-json-body)
                    (wrap-json-response)
                    (wrap-defaults site-defaults)))

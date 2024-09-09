(ns web-app.pages.todo
  (:require [web-app.api.todo :refer [get-todos]]
            [selmer.parser :refer [render-file]]))

(defn todo-list [req]
  (get-todos req))

(defn todo-form [req]
  ;; render file with selmer injecting [:session :ring.middleware.anti-forgery/anti-forgery-token]
  (render-file "todo-form.html" 
               {:csrf-token (get-in req [:session :ring.middleware.anti-forgery/anti-forgery-token])}))

#_(todo-form {:session {:ring.middleware.anti-forgery/anti-forgery-token "token"}})

(ns web-app.pages
  (:require [taoensso.timbre :as timbre]))

(defn hello [_]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "Hello, World!")})

(defn reflect [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str req)})

(defn not-found [{:keys [uri]}]
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body (str "Path Not Found: " uri)})

(defn add-todo [req]
    (let [body (-> req :body slurp)]
        (timbre/info "Received a todo:" body)
        {:status 200
         :headers {"Content-Type" "text/html"}
         :body (str "Received a todo:" body)}))

(defn todos [req]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (str "Todos")})

(ns web-app.pages.common
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

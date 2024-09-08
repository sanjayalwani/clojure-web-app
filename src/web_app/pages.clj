(ns web-app.pages)

(defn not-found [{:keys [uri]}]
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body (str "Path Not Found: " uri)})

(defn hello [_]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "Hello, World!")})

(defn reflect [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str req)})

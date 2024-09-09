(ns web-app.api.todo
  (:require [taoensso.timbre :as timbre]
            [ring.util.response :refer [response status content-type]]
            [web-app.db :refer [db]]
            [web-app.db.todo :as todo]))

(defn add-todo [req]
  (timbre/info "Received a request to add a todo" req)
  (let [todo-description (get-in req [:body "todo"])]
    (timbre/info "Received a todo:" todo-description)
    (if (nil? todo-description)
      (-> {:errors ["No todo description provided"]}
          response
          (status 400))
      (let [amount-added (todo/insert-todo db {:description todo-description})]
       (if (> amount-added 0)
         (-> {:added amount-added}
             response) 
         (-> {:errors [(str "Failed to add todo: " todo-description)]}
             response
             (status 400)))))))

(defn get-todos [req]
  (let [todos (todo/list-todos db)]
    (timbre/info "Retrieved todos:" todos)
    (content-type
     (response 
     (str "<ul>"
          (apply str (map #(str "<li>" (:description %) "</li>") todos))
          "</ul>"))
     "text/html")))

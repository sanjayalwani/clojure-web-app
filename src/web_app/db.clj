(ns web-app.db
  (:require [hugsql.core :as hugsql]))

(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/database.db"})

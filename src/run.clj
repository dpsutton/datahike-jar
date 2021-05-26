(ns run
  (:require [datahike.api :as d]))

(def cfg {:store              {:backend :mem
                               :id      "default"}
          :name               "datahike"
          :schema-flexibility :write
          :keep-history?      true})



(d/create-database cfg)

(def conn (d/connect cfg))

;; the first transaction will be the schema we are using
;; you may also add this within database creation by adding :initial-tx
;; to the configuration
(d/transact conn [{:db/ident :name
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one }
                  {:db/ident :age
                   :db/valueType :db.type/long
                   :db/cardinality :db.cardinality/one }])

;; lets add some data and wait for the transaction
(d/transact conn [{:name  "Alice", :age   20 }
                  {:name  "Bob", :age   30 }
                  {:name  "Charlie", :age   40 }
                  {:age 15 }])

(defn query
  [db]
  (d/q '[:find ?e ?n ?a
         :where
         [?e :name ?n]
         [?e :age ?a]]
       db))

(defn -main [& _]
  (println (pr-str (query @conn))))

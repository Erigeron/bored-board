
(ns server.schema )

(def user {:name nil, :id nil, :nickname nil, :avatar nil, :password nil})

(def router {:name nil, :title nil, :data {}, :router nil})

(def configs {:storage-key "/data/cumulo/bored-board.edn", :port 5021})

(def database {:sessions {}, :users {}, :board {}, :size 6, :messages {}})

(def session
  {:user-id nil,
   :id nil,
   :nickname nil,
   :color :black,
   :router {:name :home, :data nil, :router nil},
   :notifications []})

(def notification {:id nil, :kind nil, :text nil})

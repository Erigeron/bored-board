
(ns app.updater.board )

(defn decrease [db op-data session-id op-id op-time]
  (if (contains? db :size) (update db :size dec) (assoc db :size 5)))

(defn draw [db op-data session-id op-id op-time]
  (let [color (get-in db [:sessions session-id :color])]
    (assoc-in
     db
     [:board op-data]
     (if (= color :random)
       (str "#" (-> (.random js/Math) (* 16777215) (js/Math.floor) (.toString 16)))
       color))))

(defn increase [db op-data session-id op-id op-time]
  (if (contains? db :size) (update db :size inc) (assoc db :size 7)))

(defn pick [db op-data session-id op-id op-time]
  (assoc-in db [:sessions session-id :color] op-data))

(defn reset-board [db op-data session-id op-id op-time] (assoc db :board {}))

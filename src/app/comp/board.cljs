
(ns app.comp.board
  (:require [hsl.core :refer [hsl]]
            [app.schema :as schema]
            [respo.core :refer [defcomp list-> <> span div a]]
            [respo-ui.core :as ui]
            [respo.comp.space :refer [=<]]
            [respo.comp.inspect :refer [comp-inspect]]))

(defn on-draw [position]
  (fn [e dispatch! mutate!] (dispatch! :board/draw (str (:x position) "/" (:y position)))))

(def style-board
  {:display :grid,
   :justify-items :stretch,
   :align-items :stretch,
   :width 600,
   :height 600,
   :grid-gap "4px"})

(def style-container {:position :relative, :width "100%", :height "100%"})

(def style-spot {:background-color (hsl 0 0 90), :cursor :pointer, :border-radius "2px"})

(defcomp
 comp-board
 (size board)
 (let [digits (range 1 (inc size))
       spots (mapcat (fn [x] (map (fn [y] {:x x, :y y}) digits)) digits)]
   (div
    {:style (merge style-container)}
    (list->
     {:style style-board}
     (->> spots
          (map
           (fn [position]
             (let [k (str (:x position) "/" (:y position))]
               [(str (:x position) "-" (:y position))
                (div
                 {:style (merge
                          style-spot
                          {:grid-area (str (:x position) "/" (:y position))}
                          (if (contains? board k)
                            {:border-radius "50%", :box-shadow "0 0 4px gray"})
                          (if (contains? board k) {:background-color (get board k)})),
                  :on-click (on-draw position)})]))))))))


(ns app.comp.board
  (:require-macros [respo.macros :refer [defcomp <> span div a]])
  (:require [hsl.core :refer [hsl]]
            [app.schema :as schema]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [respo.comp.inspect :refer [comp-inspect]]))

(def style-board
  {:display :grid,
   :justify-items :stretch,
   :align-items :stretch,
   :width 600,
   :height 600,
   :grid-gap "4px"})

(def style-container {:position :relative, :width "100%", :height "100%"})

(def style-spot {:background-color (hsl 0 0 90), :cursor :pointer, :border-radius "2px"})

(defn on-draw [position]
  (fn [e dispatch! mutate!] (dispatch! :board/draw (str (:x position) "/" (:y position)))))

(defcomp
 comp-board
 (size board grid-area)
 (let [digits (range 1 (inc size))
       spots (mapcat (fn [x] (map (fn [y] {:x x, :y y}) digits)) digits)]
   (div
    {:style (merge style-container {:grid-area grid-area})}
    (div
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
                  :event {:click (on-draw position)}})]))))))))

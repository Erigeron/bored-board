
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
   :grid-gap "8px"})

(def style-container {:position :relative, :width "100%", :height "100%"})

(def style-colors {:position :absolute, :top 0, :width "100%", :height "100%"})

(def style-spot {:background-color (hsl 0 0 80)})

(defcomp
 comp-board
 (size board)
 (let [digits (range 1 (inc size))
       spots (mapcat (fn [x] (map (fn [y] {:x x, :y y}) digits)) digits)]
   (div
    {:style style-container}
    (div
     {:style style-board}
     (->> spots
          (map
           (fn [position]
             (let []
               [(str (:x position) "-" (:y position))
                (div
                 {:inner-text "demo",
                  :style (merge
                          style-spot
                          {:grid-area (str (:x position) "/" (:y position))})})])))))
    (div
     {:style (merge style-board style-colors)}
     (->> board
          (map
           (fn [entry]
             (let [[position color] entry]
               [(str (:x position) "-" (:y position)) (<> span color nil)])))))
    (comp-inspect "spots" spots nil ))))

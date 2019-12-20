
(ns app.comp.palette
  (:require [hsl.core :refer [hsl]]
            [app.schema :as schema]
            [respo.core :refer [defcomp list-> <> span div a]]
            [respo-ui.core :as ui]
            [respo.comp.space :refer [=<]])
  (:require-macros [clojure.core.strint :refer [<<]]))

(def color-layouts
  {:purple "1/1/1/1",
   :red "1/2/1/2",
   :yellow "1/3/1/3",
   :black "2/1/2/1",
   :random "2/2/2/2",
   :white "2/3/2/3",
   :blue "3/1/3/1",
   :cyan "3/2/3/2",
   :green "3/3/3/3"})

(defn on-pick [color-name] (fn [e dispatch! mutate!] (dispatch! :board/pick color-name)))

(def style-color {})

(def style-palette
  {:display :grid,
   :grid-template-rows "1fr 1fr 1fr",
   :grid-template-columns "1fr 1fr 1fr",
   :grid-gap "8px",
   :width 160,
   :height 160,
   :justify-content :center})

(defcomp
 comp-palette
 (color)
 (list->
  {:style (merge style-palette)}
  (->> color-layouts
       (map
        (fn [entry]
          (let [[color-name position] entry]
            [color-name
             (div
              {:style (merge
                       style-color
                       {:grid-area position,
                        :background-color color-name,
                        :border "1px solid #eee",
                        :border-radius "50%",
                        :max-width 32,
                        :max-height 32}
                       (if (= color color-name) {:max-height 1000, :max-width 1000})),
               :on-click (on-pick color-name)}
              (if (= color-name :random) (<> "Random")))]))))))


(ns app.comp.palette
  (:require-macros [respo.macros :refer [defcomp <> span div a]])
  (:require [hsl.core :refer [hsl]]
            [app.schema :as schema]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]))

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

(def style-color {:min-width 40, :min-height 40})

(def style-palette
  {:display :grid,
   :grid-template-rows "1fr 1fr 1fr",
   :grid-template-columns "1fr 1fr 1fr",
   :grid-gap "8px",
   :width 160,
   :height 160})

(defcomp
 comp-palette
 (color position)
 (div
  {:style (merge style-palette {:grid-area position})}
  (->> color-layouts
       (map
        (fn [entry]
          (let [[color-name position] entry]
            [color-name
             (div
              {:style (merge
                       style-color
                       {:grid-area position, :background-color color-name}
                       (if (= color color-name) {:border "2px solid blue"})),
               :event {:click (on-pick color-name)}})]))))))

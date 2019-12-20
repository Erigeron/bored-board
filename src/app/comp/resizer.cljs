
(ns app.comp.resizer
  (:require [hsl.core :refer [hsl]]
            [app.schema :as schema]
            [respo.core :refer [defcomp <> span button div a]]
            [respo-ui.core :as ui]
            [respo.comp.space :refer [=<]]))

(defn on-dec [e dispatch! mutate!] (dispatch! :board/decrease nil))

(defn on-inc [e dispatch! mutate!] (dispatch! :board/increase nil))

(defn on-reset [e dispatch! mutate!] (dispatch! :board/reset-board nil))

(defcomp
 comp-resizer
 (current-size)
 (div
  {:style {}}
  (<> span current-size nil)
  (=< 8 nil)
  (button {:inner-text "increase", :style ui/button, :on-click on-inc})
  (=< 8 nil)
  (button {:inner-text "decrease", :style ui/button, :on-click on-dec})
  (=< 8 nil)
  (button {:inner-text "reset", :style ui/button, :on-click on-reset})))

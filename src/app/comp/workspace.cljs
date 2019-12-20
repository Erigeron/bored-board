
(ns app.comp.workspace
  (:require [hsl.core :refer [hsl]]
            [app.schema :as schema]
            [respo.core :refer [defcomp <> span div a]]
            [respo-ui.core :as ui]
            [respo.comp.space :refer [=<]]
            [app.comp.board :refer [comp-board]]
            [app.comp.resizer :refer [comp-resizer]]
            [app.comp.palette :refer [comp-palette]]))

(def style-workspace {:overflow :auto, :padding 16})

(defcomp
 comp-workspace
 (states store)
 (div
  {:style (merge ui/row style-workspace)}
  (div
   {:style ui/flex}
   (comp-resizer (:size store))
   (=< nil 16)
   (comp-board (:size store) (:board store)))
  (comp-palette (get-in store [:session :color]))))

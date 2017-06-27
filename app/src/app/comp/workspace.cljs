
(ns app.comp.workspace
  (:require-macros [respo.macros :refer [defcomp <> span div a]])
  (:require [hsl.core :refer [hsl]]
            [app.schema :as schema]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.board :refer [comp-board]]
            [app.comp.resizer :refer [comp-resizer]]
            [app.comp.palette :refer [comp-palette]]))

(def style-workspace
  {:display :grid, :grid-template-rows "1fr 1fr 80px", :grid-template-columns "2fr 1fr"})

(defcomp
 comp-workspace
 (states store)
 (div
  {:style style-workspace}
  (comp-resizer (:size store) "3/1/3/1")
  (comp-board (:size store) (:board store) "1/1/2/1")
  (comp-palette (get-in store [:session :color]) "1/2/2/2")))

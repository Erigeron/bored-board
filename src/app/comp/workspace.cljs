
(ns app.comp.workspace
  (:require [hsl.core :refer [hsl]]
            [app.schema :as schema]
            [respo.core :refer [defcomp <> span div a]]
            [respo-ui.core :as ui]
            [respo-ui.colors :as colors]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.board :refer [comp-board]]
            [app.comp.resizer :refer [comp-resizer]]
            [app.comp.palette :refer [comp-palette]]))

(def style-workspace
  {:display :grid,
   :grid-template-rows "1fr 1fr 80px",
   :grid-template-columns "2fr 1fr",
   :grid-gap "16px"})

(defcomp
 comp-workspace
 (states store)
 (div
  {:style style-workspace}
  (comp-resizer (:size store) "3/1/4/2")
  (comp-board (:size store) (:board store) "1/1/3/2")
  (comp-palette (get-in store [:session :color]) "1/2/3/3")))

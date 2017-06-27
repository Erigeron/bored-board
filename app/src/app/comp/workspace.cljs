
(ns app.comp.workspace
  (:require-macros [respo.macros :refer [defcomp <> span div a]])
  (:require [hsl.core :refer [hsl]]
            [app.schema :as schema]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.core :refer [create-comp]]
            [respo.comp.space :refer [=<]]
            [app.comp.board :refer [comp-board]]))

(def style-workspace {:display :grid, :width "100%", :height "100%"})

(defcomp comp-workspace (states store) (div {:style style-workspace} (comp-board 12 {})))

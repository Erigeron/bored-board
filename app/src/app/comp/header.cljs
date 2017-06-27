
(ns app.comp.header
  (:require-macros [respo.macros :refer [defcomp <> span div]])
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.core :refer [create-comp]]))

(defn on-profile [e dispatch!]
  (dispatch! :router/change {:name :profile, :params nil, :router nil}))

(def style-logo {:cursor :pointer, :grid-area "1/1/1/1"})

(def style-pointer {:cursor "pointer", :grid-area "1/3/1/3"})

(def style-header
  {:background-color colors/motif,
   :justify-content :space-between,
   :padding "0 16px",
   :font-size 16,
   :color :white,
   :grid-row "1/1",
   :grid-column "1/1",
   :display :grid,
   :grid-template-rows "1fr",
   :grid-template-columns "120px 1fr 120px",
   :justify-items :center,
   :align-items :center})

(defn on-home [e dispatch!]
  (dispatch! :router/change {:name :home, :params nil, :router nil}))

(defcomp
 comp-header
 (logged-in?)
 (div
  {:style (merge style-header)}
  (div {:event {:click on-home}, :style style-logo} (<> span "B&B" nil))
  (div
   {:style style-pointer, :event {:click on-profile}}
   (<> span (if logged-in? "Me" "Guest") nil))))

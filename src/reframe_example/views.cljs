(ns reframe-example.views
  (:require [re-frame.core :as rf]
            [reframe-example.events :as events]
            [reframe-example.subs :as subs]))

(defn main-panel []
  (let [name (rf/subscribe [::subs/name])]
    [:div
     [:h1
      "Hello from " @name]
     [:button {:on-click #(rf/dispatch [::events/update-name "novo nome"])} "Update Name"]]))

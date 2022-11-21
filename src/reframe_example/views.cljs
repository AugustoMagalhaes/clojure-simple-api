(ns reframe-example.views
  (:require [re-frame.core :as rf]
            [reframe-example.events :as events]
            [reframe-example.subs :as subs]))

(defn main-panel []
  (let [name (rf/subscribe [::subs/name])
        loading (rf/subscribe [::subs/loading])]
    [:div
     [:h1
      "Hello from " @name]
     (when @loading "Loading...")
     [:button {:on-click #(rf/dispatch [::events/fetch-cats-info])} "Make API Call"]
     [:button {:on-click #(rf/dispatch [::events/update-name "novo nome"])} "Update Name"]]))

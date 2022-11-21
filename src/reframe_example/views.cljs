(ns reframe-example.views
  (:require [re-frame.core :as rf]
            [reframe-example.events :as events]
            [reframe-example.subs :as subs]))

(defn display-cats-info
  [[val]]
  (prn "val: " val)
  [:div
   [:p {:style {:font-size "20px" :color "#d54728" :font-family "Helvetia"}}val]])

(defn main-panel []
  (let [name (rf/subscribe [::subs/name])
        loading (rf/subscribe [::subs/loading])
        cats (rf/subscribe [::subs/cats-info])]
    [:div
     [:h1
      "Hello from " @name]
     (when @loading "Loading...")
     (when @cats (display-cats-info @cats))
     [:button {:on-click #(rf/dispatch [::events/fetch-cats-info])} "Make API Call"]
     [:button {:on-click #(rf/dispatch [::events/update-name "novo nome"])} "Update Name"]]))

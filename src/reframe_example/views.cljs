(ns reframe-example.views
  (:require [re-frame.core :as rf]
            [reframe-example.events :as events]
            [reframe-example.subs :as subs]))

(defn display-cats-info
  [[val]]
  (prn "val: " val)
  [:div
   [:p.cat-info val]])

(defn main-panel []
  (let [name (rf/subscribe [::subs/name])
        loading (rf/subscribe [::subs/loading])
        cats (rf/subscribe [::subs/cats-info])]
    [:div.container
     [:h1
      "Hello from " @name]
     (when @loading
       [:div.loader-wrapper
        [:div.loader-container
         [:div.loader
          [:p.loading "Loading..."]]]])
     (when @cats (display-cats-info @cats))
     [:button.call {:on-click #(rf/dispatch [::events/fetch-cats-info])} "Make API Call"]
     [:button.update {:on-click #(rf/dispatch [::events/update-name "novo nome"])} "Update Name"]]))

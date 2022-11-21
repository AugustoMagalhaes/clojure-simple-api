(ns reframe-example.views
  (:require [re-frame.core :as rf]
            [reframe-example.events :as events]
            [reframe-example.subs :as subs]))

(defn display-cats-info
  [[val]] 
  [:div
   [:p.cat-info val]])

(defn main-panel []
  (let [name (rf/subscribe [::subs/name])
        loading (rf/subscribe [::subs/loading])
        cats (rf/subscribe [::subs/cats-info])]
    [:div.container
     [:h1
      "Bem-vindo(a) " @name "!"]
     (when @loading
       [:div.loader-wrapper
        [:div.loader-container
         [:div.loader
          [:p.loading "Loading..."]]]])
     (when @cats (display-cats-info @cats))
     [:label {:style {:font-size "18px", :padding-right "5px"}}"Digite seu nome:"
      [:input.input-name {:type "text" :value @name :on-change #(rf/dispatch [::events/update-name (-> % .-target .-value)])}]]
     [:button.call {:on-click #(rf/dispatch [::events/fetch-cats-info])} "Make API Call"]
     [:button.update {:on-click #(rf/dispatch [::events/update-name "AugustoMagalhaes"])} "Meu usuário Github"]]))




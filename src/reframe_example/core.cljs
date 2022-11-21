(ns reframe-example.core
  (:require [re-frame.core :as rf]
            [reagent.dom :as rdom]
            [reframe-example.config :as config]
            [reframe-example.events :as events]
            [reframe-example.views :as views]))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))

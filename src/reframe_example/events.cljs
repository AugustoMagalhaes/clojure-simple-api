(ns reframe-example.events
  (:require [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]
            [reframe-example.db :as db]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

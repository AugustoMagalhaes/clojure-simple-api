(ns reframe-example.events
  (:require [ajax.core :as ajax]
            [day8.re-frame.http-fx]
            [day8.re-frame.tracing :refer-macros [fn-traced]]
            [re-frame.core :as rf]
            [reframe-example.db :as db]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(rf/reg-event-db
 ::update-name
 (fn [db [_ val]]
   (assoc db :name val)))

(rf/reg-event-fx                             ;; note the trailing -fx
  ::fetch-cats-info                      ;; usage:  (dispatch [:handler-with-http])
  (fn [{:keys [db]} _]                    ;; the first param will be "world"
    {:db   (assoc db :loading true)   ;; causes the twirly-waiting-dialog to show??
     :http-xhrio {:method          :get
                  :uri             "https://meowfacts.herokuapp.com/"
                  :timeout         8000                                           ;; optional see API docs
                  :response-format (ajax/json-response-format {:keywords? true})  ;; IMPORTANT!: You must provide this.
                  :on-success      [::fetch-cats-info-success]
                  :on-failure      [:bad-http-result]}}))

(rf/reg-event-db
 ::fetch-cats-info-success
 (fn [db [_ {:keys [data]}]]
   (prn (first data))
   (-> db
       (assoc :loading false)
       (assoc :cats-info data))))


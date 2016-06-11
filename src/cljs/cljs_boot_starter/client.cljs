(ns cljs-boot-starter.client
  (:require [reagent.core :as ra :refer [atom render]]
            [goog.events :as events]
            [goog.dom :as dom]))

(enable-console-print!)

;; -----------------------------------------------------------
;; This function take Integer values and return Integer values

(defn calculate []
  (let [num1 (int (.-value (dom/getElement "num1")))
        num2 (int (.-value (dom/getElement "num2")))
        slct (.-value (dom/getElement "sl"))]

    (cond
      (= slct "ad") (+ num1 num2)
      (= slct "sb") (- num1 num2)
      (= slct "ml") (* num1 num2)
      (= slct "dv") (quot num1 num2))))

;; This is UI part.....................

(defn calculator []
  [:div
   [:span "Simple Calculator : "
    [:input {:type "number"
             :id "num1"
             :placeholder "Enter First Number"}]
    [:span " "]

    [:select {:id "sl"}

     [:option {:label "select operator"}]

     [:option {:label "+"
               :value "ad"}]
     [:option {:label "-"
               :value "sb"}]
     [:option {:label "*"
               :value "ml"}]
     [:option {:label "/"
               :value "dv"}]]
    [:span " "]

    [:input {:type "number"
             :id "num2"
             :placeholder "Enter Second Number"}]
    " "
    [:input {:type "button"
             :value "="
             :on-click #(do
                          (js/console.log (calculate))
                          (set! (.-innerHTML (.getElementById js/document "result1")) (calculate)))}]
    [:span " "]
    [:span {:id "result1"}]]])

;; ---------------------------------
;; Check password is matched or not!

;; this function return boolean
(defn check-password-values []
  (let [pass1 (.-value (dom/getElement "pass1"))
        pass2 (.-value (dom/getElement "pass2"))]
    (if (= pass1 pass2)
      true
      false)))

;; this is the UI part
(defn check-password []
  [:div
   [:span "Check Password : "]
   [:span
    [:input {:type "password"
             :id "pass1"
             :required "required"
             :placeholder "Enter Password"}]]
   [:span " "]
   [:span
    [:input {:type "password"
             :id "pass2"
             :placeholder "Re-enter Password"}]]
   [:span " "]
   [:span
    [:input {:type "button"
             :value "check"
             :on-click #(do
                          (js/console.log (check-password-values))
                          ;; (set! (.-innerHTML (.getElementById js/document "result2")) (check-password-values))
                          (.alert js/window (apply str (concat "Your password is : " (str (check-password-values))))))}]]
   [:span " "]
   [:span {:id "result2"}]])


;; --------------------------------
;; Reagent/render part

(defn init []
  (do (render [calculator] (.getElementById js/document "calculator-area"))
      (render [check-password] (.getElementById js/document "check-password-area"))))

(init)

(ns learn-cljs.temp-converter
  (:require [goog.dom :as gdom]
            [goog.events :as gevents]))

(defn f->c [deg-f] 
  (/ (- deg-f 32) 1.8))

(defn c->f [deg-c] 
  (+ (* deg-c 1.8) 32))

(def celsius-radio (gdom/getElement "unit-c"))
(def fahrenheit-radio (gdom/getElement "unit-f"))
(def temp-input (gdom/getElement "temp"))
(def output-target (gdom/getElement "temp-out"))
(def output-unit-target (gdom/getElement "unit-out"))

(defn get-input-unit [] 
  (if (.-checked celsius-radio)
  :celsius
  :fahrenheit))

(defn get-input-temp []
  (js/parseInt (.-value temp-input)))

(defn set-output-temp [temp] 
  (gdom/setTextContent output-target (.toFixed temp 2)))

(defn get-app-element []
  (gdom/getElement "app"))



;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

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
(def clear-button (gdom/getElement "clear-input"))

(defn get-input-unit []
  (if (.-checked celsius-radio)
    :celsius
    :fahrenheit))

(defn get-input-temp []
  (js/parseInt (.-value temp-input)))

(defn set-output-temp [temp]
  (gdom/setTextContent output-target (.toFixed temp 2)))

(defn clear-input [] 
  (set! (.-value temp-input) "")
  (gdom/setTextContent output-target "-"))

(defn target-label-for-output-unit [unit]
  (case unit
    :fahrenheit "C"
    :celsius "F"))

(defn convert [unit temp]
  (if (= unit :celsius)
    (c->f temp)
    (f->c temp)))

(defn update-output [_]
  (let [unit (get-input-unit)
        input-temp (get-input-temp)
        output-temp (convert unit input-temp)
        output-label (target-label-for-output-unit unit)]
    (set-output-temp output-temp)
    (gdom/setTextContent output-unit-target output-label)))

(gevents/listen temp-input "keyup" update-output)
(gevents/listen celsius-radio "click" update-output)
(gevents/listen fahrenheit-radio "click" update-output)
(gevents/listen clear-button "click" clear-input)

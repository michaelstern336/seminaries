(ns semantics.human-template
  (:require [clj-http.client :as c]
           [clojure.data.json :as json]))

;; translation API setup
(def yandex-api {:key "trnsl.1.1.20171123T091345Z.157f801221646c83.c9c76d739e4b9614c0ded55e10f88434b98412ee"
                 :url "https://translate.yandex.net/api/v1.5/tr.json/translate"})

;; utilities
(defn to-hex [c])

(defn sanitize [c])

(defn sanitize-all [s])

;; build request for yandex API
(defn aggregate-url [text [from to :as lang]])

(defn call-api [text [from to :as lang]])


(defn translate [text [from to :as lang]])

(defn delayed-translate [text [from to :as lang]])

(defn triple-translate [text])









(defn acronymize [text])

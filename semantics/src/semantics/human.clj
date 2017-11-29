(ns semantics.human
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

;; translation API setup
(def yandex-api {:key "trnsl.1.1.20171123T091345Z.157f801221646c83.c9c76d739e4b9614c0ded55e10f88434b98412ee"
                 :url "https://translate.yandex.net/api/v1.5/tr.json/translate"})

;; utilities
(defn to-hex [c]
  (format "%x" (int c)))

(defn sanitize [c]
  (if (some #{c} [\space \& \% \$ \* \( \) \[ \]])
    (str "%" (to-hex c))
    c))

(defn sanitize-all [s]
  (reduce #(str %1 (sanitize %2)) "" s))

;; build request for yandex API
(defn aggregate-url [text [from to :as lang]]
  (str (yandex-api :url)
       "?key="
       (yandex-api :key)
       "&text="
       text
       "&lang=" from "-" to))

(defn call-api [text [from to :as lang]]
  (:body (client/get (aggregate-url text lang) {:accept :json})))


(defn translate [text [from to :as lang]]
  (let [translation (-> (call-api text lang)
                        (json/read-str :key-fn keyword)
                        :text)]
    (apply str translation)))

(defn delayed-translate [text [from to :as lang]]
  (let [good-text (sanitize-all text)
        translation (translate good-text lang)
        _ (prn "Translation of " good-text " from " from " to " to " is: " translation)]
    (Thread/sleep 1000)
    translation))

(defn triple-translate [text]
  (reduce  delayed-translate
           text
           [["en" "ru"] ["ru" "ro"] ["ro" "en"]]))

(defn acronymize [text]
  (let [[w1 w2 w3] (clojure.string/split text #" ")]
    (if (or true (-> w2 nil? not))
      (str (first w1) w2 (first w3))
      text)))

(ns semantics.clojure
  (:require [semantics.human :as sh]
            [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]))

(defn has-spaces? [s]
  (clojure.string/includes? s " "))

(s/fdef sh/acronymize
        :args (s/cat :text string?)
        :ret string?
        :fn (s/and #(if (-> % :args :text has-spaces? not)
                      (= (:ret %)
                         (-> % :args :text))
                     (-> % :ret has-spaces? not))
                   #(<= (-> % :ret count) (-> % :args :text count))))

(stest/instrument `sh/acronymize)
(stest/summarize-results (stest/check `sh/acronymize))




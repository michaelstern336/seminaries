(ns semantics.clojure-template
  (:require ))

(defn has-spaces? [s])

(s/fdef sh/acronymize
        :args 
        :ret 
        :fn )

(stest/instrument `sh/acronymize)
(stest/sumarize-results (stest/check `sh/acronymize))


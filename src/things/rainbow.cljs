(ns things.rainbow
  (:require [things.colors :as colors]))

(defn draw [s]
  (js/push)
    (doseq [x (range 0 100 10)]
      (js/fill x 100 50)
      (js/circle 0 0 (- s (* 1.5 x))))
    (apply js/fill colors/skyblue)
    (js/circle 0 0 (- s 120))
  (js/pop))

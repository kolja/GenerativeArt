(ns things.hills
  (:require [things.colors :as colors]))

(defn hill [x y s c]
  (js/push)
  (apply js/fill c)
  (js/circle x y s)
  (js/pop))

(defn draw []
  (let [width js/window.innerWidth
        height js/window.innerHeight]
    (hill (- width 50) 570 400 [50 100 40])
    (hill 370 550 400 [50 100 35])
    (hill 150 500 400 [50 100 40])
    (hill 300 800 700 [40 100 40])
    (hill (- width 200) (+ height 200) 800 [35 100 35])
    (hill 500 (+ height 350) 900 [18 100 30])
  ))

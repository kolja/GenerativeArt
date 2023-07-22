(ns things.sky
  (:require [things.colors :as colors]))

(defn sun [x y]
  (js/push)
  (js/fill 100 100 100 30)
  (js/circle x y 250)
  (js/fill 15 100 60)
  (js/circle x y 150)
  (js/fill 15 100 100 60)
  (js/circle x y 100)
  (js/pop))

(defn draw [width height]
    (js/push)
    (apply js/fill colors/skyblue)
    (js/rect 0 0 width height)
    (sun (- width 200) 150)
    (js/pop))

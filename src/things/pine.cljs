(ns things.pine
  (:require [things.colors :as colors]))

(defn recursive-pine [i]
  (let [s (* i 2)]
    (js/push)
      (apply js/fill colors/pinegreen)
      (js/triangle (- s) (- s) 0 (* -2 s) s (- s))
      (js/translate 0 (* s -0.8))
      (when (> i 0) (recursive-pine (dec i)))
    (js/pop)))

(defn draw [x y i]
    (js/push)
      (js/translate x (- js/window.innerHeight y))
      (js/fill 20 80 20 20)
      (js/ellipse 0 0 40 20)
      (apply js/fill colors/pinewood)
      (js/rect -5 -40 10 40)
      (recursive-pine i)
    (js/pop))


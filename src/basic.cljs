
(ns basic
  "draw a red cube on a yellow background just to get things off the ground"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn setup []
  (js/createCanvas 500 500)
  (js/colorMode js/HSL 100))

(defn draw []
  (js/background 15 70 100)
  (js/pushpop)
    (js/translate 300 200)
    (js/fill 50 100) 45
    (js/rotate (* 0.002 (system-time) ))
    (js/rect 0 0 100 100)
  (js/pop))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw))



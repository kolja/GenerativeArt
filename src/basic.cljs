
(ns basic
  "draw a red cube on a yellow background just to get things off the ground"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn setup []
  (js/createCanvas 500 500)
  (js/colorMode js/HSL 100)
  (js/noStroke))

(defn draw []
  (js/background 15 100 70)
  (js/translate 300 200)
  (js/fill 0 100 45)
  (js/rect 0 0 100 100))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw))



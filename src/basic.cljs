
(ns basic
  "draw a red cube on a yellow background just to get things off the ground"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn setup []
  (js/createCanvas js/window.innerWidth js/window.innerHeight)
  (js/colorMode js/HSL 100))

(defn draw []
  (js/background 15 100 70)
  (js/push)
    (js/translate 300 200)
    (js/fill 100 100 45)
    (js/rect 0 0 100 100)
  (js/pop))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw))



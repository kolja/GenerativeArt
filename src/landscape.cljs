
(ns landscape
  "draw a landscape from primitives"
  (:require
    [things.sky :as sky]
    [things.hills :as hills]
    [things.cloud :as cloud]
    [things.rainbow :as rainbow]
    [things.pine :as pine]
    [goog.object :as g]
    [p5 :as p5]))

(defn createCanvas []
  (def width js/window.innerWidth)
  (def height js/window.innerHeight)
  (js/createCanvas js/window.innerWidth js/window.innerHeight))

(defn setup []
  (createCanvas)
  (js/noStroke)
  (js/colorMode js/HSL 100))

(defn draw []
    (sky/draw width height)
    (js/push)
      (js/translate -50 520)
      (rainbow/draw 1000)
    (js/pop)

    (hills/draw)
    (cloud/draw (mod (* (system-time) 0.1) width) 100)
    (cloud/draw (mod (* (system-time) 0.08) width) 190)
    (pine/draw 200 100 10)
    (pine/draw 170 140 8)
    (pine/draw 130 80 10)
    (pine/draw (- width 300) 150 10)
    (pine/draw (- width 270) 180 8)
    (pine/draw (- width 230) 130 10)
    (pine/draw (- width 150) 150 8))

(defn mousePressed []
  (if (js/isLooping) (js/noLoop) (js/loop)))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw)
  (g/set "mousePressed" mousePressed)
  (g/set "windowResized" createCanvas))



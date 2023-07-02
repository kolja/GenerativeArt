
(ns tree
  "recursively render a tree"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn createCanvas []
  (js/createCanvas js/window.innerWidth js/window.innerHeight))

(defn setup []
  (createCanvas)
  (js/noStroke)
  (js/colorMode js/HSL 100))

(defn tree [iter rot]
  (let [s (+ 1 (/ 1 iter))
        r (* rot js/HALF_PI)]
    (js/push)
    (js/scale s)
    (js/rotate r)
    (js/fill 40 90 40)
    (js/circle 0 -40 40)
    (js/fill 10 90 40)
    (js/rect -5 -40 10 40)
    (when (< iter 3)
      (do
        (js/push)
        (js/translate 0 (* s 40))
        (tree (inc iter) (- (* (rand) 2) 1))
        (js/pop)
        ))
    (js/pop)))

(defn draw []
  (let [width js/window.innerWidth
        height js/window.innerHeight]
    (js/translate (/ width 2) (- height 100))
    (tree 1 0)
  ))

(defn ^:dev/after-load start []
  (js/console.log "start"))

(defn mousePressed []
  (if (js/isLooping) (js/noLoop) (js/loop)))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw)
  (g/set "mousePressed" mousePressed)
  (g/set "windowResized" createCanvas))



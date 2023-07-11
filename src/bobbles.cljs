
(ns bobbles
  "render a grid of boxes with doseq. Boxes in the grid have random sizes"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn createCanvas []
  (js/createCanvas js/window.innerWidth js/window.innerHeight))

(defn setup []
  (createCanvas)
  (js/noStroke)
  (js/colorMode js/HSL 100))

(defn t [ms] (/ (system-time) ms))

(defn deviate [x y]
  (let [dx (- x js/mouseX)
        dy (- y js/mouseY)
        dist (js/Math.sqrt (+ (* dx dx) (* dy dy)))
        dev (if (> dist 0) (/ 5 (js/Math.sqrt dist)) 0)]
    [(* dx dev) (* dy dev)]))

(defn drawcube [x y]
  (let [zoom 0.2
        z (js/noise (* zoom x) (* zoom y) (t 4000))
        s (* z z 70)
        s2 (if (< s 5) 0 (* 3 s))] ;; like s but with cutoff: small bobbles are of size zero
    (js/push)
      (js/translate (* 22 x) (* 22 y))
      (apply js/translate (deviate (* 22 x) (* 22 y)))
      (js/fill s 90 40 s2)
      (js/circle 0 0 s)
    (js/pop)))

(defn draw []
  (let [width js/window.innerWidth
        height js/window.innerHeight]
  (js/clear)
  (doseq [x (range (/ height 20)) y (range (/ width 20))]
    (drawcube y x)))
  )

(defn ^:dev/after-load start []
  (js/console.log "start"))

(defn mousePressed []
  (if (js/isLooping) (js/noLoop) (js/loop)))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw)
  (g/set "mousePressed" mousePressed)
  (g/set "windowResized" createCanvas))



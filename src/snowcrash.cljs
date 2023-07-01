
(ns snowcrash
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

(defn drawcube [x y]
  (let [s (+ 5 (rand-int 40))
        h (mod (+ (t 700) (- (rand-int 10) 5)) 100)
        b (+ 40 (rand-int 30) (* (Math/sin (t 500)) 10))]
    (js/push)
    (js/translate (* 22 x) (* 22 y))
    (js/fill h 90 b)
    (js/rect (- (/ s 2)) (- (/ s 2)) s s)
    (js/pop)))

(defn draw []
  (let [width js/window.innerWidth
        height js/window.innerHeight]
  (doseq [x (range (/ height 20)) y (range (/ width 20))]
    (drawcube y x))))

(defn ^:dev/after-load start []
  (js/console.log "start"))

(defn mousePressed []
  (if (js/isLooping) (js/noLoop) (js/loop)))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw)
  (g/set "mousePressed" mousePressed)
  (g/set "windowResized" createCanvas))



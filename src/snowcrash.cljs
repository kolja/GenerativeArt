
(ns snowcrash
  "render a grid of boxes with doseq. Boxes in the grid have random sizes"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn setup []
  (js/createCanvas js/window.innerWidth js/window.innerHeight)
  (js/noStroke))

(defn drawcube [x y]
  (let [s (+ 5 (rand-int 40))
        t (Math/sin (/ (system-time) 500))
        c (+ 100 (* t 50) (rand-int 75))
        ]
    (js/push)
    (js/translate (* 22 x) (* 22 y))
    (js/fill c c c)
    (js/rect (- (/ s 2)) (- (/ s 2)) s s)
    (js/pop)))

(defn draw []
  (doseq [x (range 20) y (range 20)]
    (drawcube y x)))

(defn ^:dev/after-load start []
  (js/console.log "start"))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw))



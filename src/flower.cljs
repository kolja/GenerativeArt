(ns flower
  "uses recursion to draw many cubes -- the result looks like a flower"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn setup []
  (js/createCanvas js/window.innerWidth js/window.innerHeight)
  (js/noStroke)
  #_(js/rectMode js/CENTER))

(defn drawcube [n]
  (let [color  (- 255 n)
        s (* n 1.3)]
    (js/fill 100 color n)
    (js/rect (- (/ s 2)) (- (/ s 2)) s s)
    (js/rotate (/ js/PI 10))))

(defn cube [n]
  (when (> n 0)
    (drawcube n)
    (cube (- n 7))))

(defn draw []
  (js/background 230)
  (js/translate 250 250)
  (cube 250))

(defn ^:dev/after-load start []
  (js/console.log "start"))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw))



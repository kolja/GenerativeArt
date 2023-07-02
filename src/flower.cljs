(ns flower
  "uses recursion to draw many cubes -- the result looks like a flower"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn setup []
  (js/createCanvas js/window.innerWidth js/window.innerHeight)
  (js/noStroke)
  (js/colorMode js/HSL 100)
  )

(defn drawcube [n]
  (let [color (mod (* n 1.2) 100)]
    (js/push)
    (js/scale n)
    (js/fill color 100 50)
    (js/rect -5 -5 10 10)
    (js/pop)
    (js/rotate (/ js/PI 10))
    ))

(defn cube [n]
  (when (> n 0)
    (drawcube n)
    (cube (dec n))))

(defn draw []
  (js/background 230)
  (js/translate 250 250)
  (cube 50))

(defn ^:dev/after-load start []
  (js/console.log "start"))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw))



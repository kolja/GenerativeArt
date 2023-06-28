(ns recursion01
  (:require [goog.object :as g]
  [p5 :as p5]))

(defn setup []
  (js/createCanvas js/window.innerWidth js/window.innerHeight)
  (js/rectMode js/CENTER)
  ;; (js/noStroke)
  )

(defn neg [n] (* -1 n))

(defn drawcube [n]
  (let [color n]
    (js/rotate (/ js/PI 7))
    (js/translate (neg (/ n 8)) (neg (/ n 8)))
    (js/fill color 100 100)
    (js/rect (neg n) (neg n) n n)
    ))

(defn cube [n]
  (drawcube n)
  (when (> n 0) (cube (- n 20))))

(defn draw []
  (js/background 230)
  (js/translate 300 300)
  (cube 250))

;; let a = atan2(mouseY - height / 2, mouseX - width / 2);

(defn ^:dev/after-load start []
  (js/console.log "start"))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw)
  )



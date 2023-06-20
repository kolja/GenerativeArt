(ns recursion01
  (:require [goog.object :as g]
  [p5 :as p5]))

(defn setup []
  (js/createCanvas js/window.innerWidth js/window.innerHeight)
  (js/rectMode js/CENTER)
  ;; (js/noStroke)
  )

(defn cube [n]
  (let [color n]
    (js/fill color 100 100)
    (js/rect n n n n)
    (when (> n 0) (cube (- n 5)))))

(defn draw []
  (js/background 230)
  (cube 250)
  )
;; let a = atan2(mouseY - height / 2, mouseX - width / 2);

(defn ^:dev/after-load start []
  (js/console.log "start"))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw)
  )



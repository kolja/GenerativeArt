(ns sketch
  (:require [goog.object :as g]
  [p5 :as p5]))

(defn setup []
  (js/createCanvas js/window.innerWidth js/window.innerHeight)
  (js/rectMode js/CENTER)
  (js/noStroke)
  )

(defn draw []
  (js/background 0)
  (doseq [i (range (/ js/width 4))]
    (let [x (* i 4)
          y (/ js/height 2)
          time (* js/frameCount 0.05)
          theta (+ (* i 0.05) time)
          r (* (+ (* (Math/sin theta) 0.5) 0.5) 255)]
      (js/fill r)
      (js/circle x y (+ r 50)))))

(defn ^:dev/after-load start []
  (js/console.log "start"))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw)
  )





(ns landscape
  "draw a landscape from primitives"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn createCanvas []
  (js/createCanvas js/window.innerWidth js/window.innerHeight))

(defn setup []
  (createCanvas)
  (js/noStroke)
  (js/colorMode js/HSL 100)
  (def green [40 90 40])
  (def hillgreen [45 80 36])
  (def roof [25 100 34])
  (def house [40 32 91])
  (def brown [10 90 40])
  (def skyblue [60 100 70])
  (def yellow [50 100 50])
  )

(defn tree [x y]
    (js/push)
    (js/translate x y)
    (apply js/fill green)
    (js/circle 0 -40 40)
    (apply js/fill brown)
    (js/rect -5 -40 10 40)
    (js/pop))

(defn sun [x y]
  (js/push)
  (apply js/fill [100 100 100 30])
  (js/circle x y 250)
  (apply js/fill [15 100 60])
  (js/circle x y 150)
  (apply js/fill [15 100 100 60])
  (js/circle x y 100)
  (js/pop)
  )

(defn sky [t]
  (let [width js/window.innerWidth
        height js/window.innerHeight]
    (js/push)
    (apply js/fill skyblue)
    (js/rect 0 0 width height)
    (sun (- width 100) 100)
    (js/pop)))

(defn hill [x y s]
  (js/push)
  (apply js/fill green)
  (js/circle x y s)
  (js/pop))

(defn hills []
  (hill 300 700 700))

(defn draw []
  (let [width js/window.innerWidth
        height js/window.innerHeight]
    (sky 0)
    (hills)
    (tree 150 (- height 290))
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




(ns landscape
  "draw a landscape from primitives"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn createCanvas []
  (def width js/window.innerWidth)
  (def height js/window.innerHeight)
  (js/createCanvas js/window.innerWidth js/window.innerHeight))

(defn setup []
  (createCanvas)
  (js/noStroke)
  (js/colorMode js/HSL 100)
  (def pinegreen [40 90 20])
  (def pinewood [10 90 40])
  (def skyblue [60 100 70]))

(defn cloud [x y]
  (js/push)
  (js/translate x y)
  (apply js/fill [100 0 100])
  (js/rect -50 10 140 40)
  (js/circle 0 0 100)
  (js/circle -50 30 40)
  (js/circle 50 15 70)
  (js/circle 50 -25 40)
  (js/circle 90 30 40)
  (js/pop))

(defn rainbow [s]
  (js/push)
    (doseq [x (range 0 100 10)]
      (js/fill x 100 50)
      (js/circle 0 0 (- s (* 1.5 x))))
    (apply js/fill skyblue)
    (js/circle 0 0 (- s 120))
  (js/pop))

(defn recursive-pine [i]
  (let [s (* i 2)]
    (js/push)
      (apply js/fill pinegreen)
      (js/triangle (- s) (- s) 0 (* -2 s) s (- s))
      (js/translate 0 (* s -0.8))
      (when (> i 0) (recursive-pine (dec i)))
    (js/pop)))

(defn pine [x y i]
    (js/push)
      (js/translate x (- height y))
      (js/fill 20 80 20 20)
      (js/ellipse 0 0 40 20)
      (apply js/fill pinewood)
      (js/rect -5 -40 10 40)
      (recursive-pine i)
    (js/pop))

(defn sun [x y]
  (js/push)
  (js/fill 100 100 100 30)
  (js/circle x y 250)
  (js/fill 15 100 60)
  (js/circle x y 150)
  (js/fill 15 100 100 60)
  (js/circle x y 100)
  (js/pop))

(defn sky []
  (let [width js/window.innerWidth
        height js/window.innerHeight]
    (js/push)
    (apply js/fill skyblue)
    (js/rect 0 0 width height)
    (sun (- width 200) 150)
    (js/pop)))

(defn hill [x y s c]
  (js/push)
  (apply js/fill c)
  (js/circle x y s)
  (js/pop))

(defn hills []
  (hill (- width 50) 570 400 [50 100 40])
  (hill 370 550 400 [50 100 35])
  (hill 150 500 400 [50 100 40])
  (hill 300 800 700 [40 100 40])
  (hill (- width 200) (+ height 200) 800 [35 100 35])
  (hill 500 (+ height 350) 900 [18 100 30]))

(defn draw []
    (sky)
    (js/push)
      (js/translate -50 520)
      (rainbow 1000)
    (js/pop)
    (hills)
    (cloud (mod (* (system-time) 0.08) width) 100)
    (cloud (mod (* (system-time) 0.1) width) 190)
    (pine 200 100 10)
    (pine 170 140 8)
    (pine 130 80 10)
    (pine (- width 300) 150 10)
    (pine (- width 270) 180 8)
    (pine (- width 230) 130 10)
    (pine (- width 150) 150 8))

(defn mousePressed []
  (if (js/isLooping) (js/noLoop) (js/loop)))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw)
  (g/set "mousePressed" mousePressed)
  (g/set "windowResized" createCanvas))



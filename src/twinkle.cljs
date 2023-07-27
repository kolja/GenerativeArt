
(ns twinkle
  "starry night with twinkling stars"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn createCanvas []
  (js/createCanvas js/window.innerWidth js/window.innerHeight))

(defn setup []
  (createCanvas)
  (js/noStroke)
  (js/noiseDetail 8 0.4)
  (js/colorMode js/HSL 100))

(defn t [ms] (/ (system-time) ms))

(defn star [size x y]
  (js/push)
    (js/translate x y)
    (js/scale size)
    (js/fill 15 100 70)
    (js/beginShape)
       (js/vertex 0 -4)
       (js/vertex 1 -1)
       (js/vertex 3 0)
       (js/vertex 1 1)
       (js/vertex 0 4)
       (js/vertex -1 1)
       (js/vertex -3 0)
       (js/vertex -1 -1)
    (js/endShape)
    (js/fill 15 100 90 20)
    (js/circle 0 0 (* 0.2 size))
  (js/pop)
)

(defn twinkle-value [speed amplitude offset]
  (+ (* amplitude (+ (js/sin (+ (t speed) offset)) 1)) 5)
)

(defn draw []
  (let [width js/window.innerWidth
        height js/window.innerHeight
        noise-offset 10000]
    (js/background 55 75 25)
    (doseq [x (range 20)]
      (star (twinkle-value 100 10 x)
            (* (js/noise (* x 1000) (t 4000)) width)
            (* (js/noise (+ (* x 1000) noise-offset) (t 4000)) height)
      )
    )
 ))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw))



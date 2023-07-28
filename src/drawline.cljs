
(ns drawline
  "draw lines following the mouse cursor"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn setup []
  (js/createCanvas js/window.innerWidth js/window.innerHeight)
  (js/colorMode js/HSL 100)
  (js/frameRate 10)
  (def points (atom [])))

(defn take-last-20 [v]
  (let [add-point (conj v [js/mouseX js/mouseY])]
    (into [] (take-last 20 add-point))))

(defn draw []
  (js/background 15 100 70)
  (js/stroke 20 90 30)
  (js/strokeWeight 3)
  (swap! points take-last-20)
  ;; (doseq [[p1 p2] (partition 2 1 @points)]
  (doseq [p1 (take 10 @points)
          p2 (take-last 10 @points)]
    (js/line (get p1 0) (get p1 1) (get p2 0) (get p2 1)))
)

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw))




(ns cloud
  "draw a cloud"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn createCanvas []
  (js/createCanvas js/window.innerWidth js/window.innerHeight))

(defn create-cloud-base [n]
  (into [] (map #(+ 50 (* 10 (rand-int 10))) (range n))))

(defn setup []
  (createCanvas)
  (def cloud-base (atom (create-cloud-base 5)))
  (js/noStroke)
  (js/colorMode js/HSL 100))

(defn draw-cloud []
  (let [distances (map (fn [[a b]] (js/Math.sqrt (* a b)))
                       (partition 2 1 @cloud-base))
        sum (reduce (fn [acc x] (conj acc (+ x (last acc)))) [0] distances)
        sumtotal (reduce + distances)
        x-y (map vector sum @cloud-base)]
    (doseq [[x y] x-y] (js/circle x (* -0.5 y) y))
    (js/beginShape js/TESS)
      (js/vertex 0 0)
      (doseq [[x y] x-y] (js/vertex x (* -0.5 y)))
      (js/vertex sumtotal 0)
    (js/endShape js/CLOSE)))

(defn draw []
  (js/translate (- (/ js/window.innerWidth 2) 200) (/ js/window.innerHeight 2))
  (js/background 55 100 80)
  (draw-cloud))

(defn ^:dev/after-load start []
  (js/console.log "start"))

(defn mousePressed []
  (js/clear)
  (reset! cloud-base (create-cloud-base (+ 2 (rand-int 5)))))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw)
  (g/set "mousePressed" mousePressed)
  (g/set "windowResized" createCanvas))



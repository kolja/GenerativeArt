(ns things.cloud)

(defn create-cloud [bobbles]
  (atom (into [] (map #(+ 30 (* 7 (rand-int 7))) (range bobbles)))))

(defn draw-cloud [cloud]
  (let [distances (map (fn [[a b]] (js/Math.sqrt (* a b)))
                       (partition 2 1 @cloud))
        sum (reduce (fn [acc x] (conj acc (+ x (last acc)))) [0] distances)
        sumtotal (reduce + distances)
        x-y (map vector sum @cloud)]
    (doseq [[x y] x-y] (js/circle x (* -0.5 y) y))
    (js/beginShape js/TESS)
      (js/vertex 0 0)
      (doseq [[x y] x-y] (js/vertex x (* -0.5 y)))
      (js/vertex sumtotal 0)
    (js/endShape js/CLOSE)))

(defn draw [cloud x y]
  (js/push)
    (js/translate x y)
    (draw-cloud cloud)
  (js/pop))


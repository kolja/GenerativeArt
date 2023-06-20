(def w 20)
(def columns (Math/floor (/ 720 w)))
(def rows (Math/floor (/ 400 w)))
(def board (make-array columns rows))
(def next-cell (make-array columns rows))

(defn init []
  (doseq [i (range columns)]
    (doseq [j (range rows)]
      (if (or (= i 0) (= j 0) (= i (dec columns)) (= j (dec rows)))
        (aset board i j 0)
        (do
          (aset board i j (Math/floor (rand 2)))
          (aset next-cell i j 0))))))

(defn generate []
  (doseq [x (range 1 (dec columns))]
    (doseq [y (range 1 (dec rows))]
      (let [neighbors (reduce + (for [i (range -1 2)
                                      j (range -1 2)
                                      :let [xx (+ x i)
                                            yy (+ y j)]
                                      :when (and (>= xx 0) (< xx columns) (>= yy 0) (< yy rows) (not (and (= i 0) (= j 0))))]
                                 (aget board xx yy)))]
        (aset next-cell x y
              (cond
                (and (= (aget board x y) 1) (< neighbors 2)) 0
                (and (= (aget board x y) 1) (> neighbors 3)) 0
                (and (= (aget board x y) 0) (= neighbors 3)) 1
                :else (aget board x y)))))))

(defn draw-board []
  (doseq [i (range columns)]
    (doseq [j (range rows)]
      (if (= (aget board i j) 1)
        (js/fill 0)
        (js/fill 255))
      (js/stroke 0)
      (js/rect (* i w) (* j w) (dec w) (dec w)))))

(defn setup []
  (js/frame-rate 10)
  (js/create-canvas 720 400)
  (init))

(defn draw []
  (js/background 255)
  (generate)
  (draw-board))

(defn mouse-pressed []
  (init))
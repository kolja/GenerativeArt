
(ns GameOfLife
  "The Game of Life"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn createCanvas []
  (js/createCanvas js/window.innerWidth js/window.innerHeight))

(defn create-grid [x y])

(def cell-size 16)
(def width (quot js/window.innerWidth cell-size))
(def height (quot js/window.innerHeight cell-size))
(def grid (atom (into [] (repeat (* width height) 0))))
(def editmode (atom true))

(defn get-cell [x y]
  (let [idx (+ (* (mod y height) width) (mod x width))]
    (nth @grid idx)))

(defn set-cell [x y new-value]
  (let [idx (+ (* (mod y height) width) (mod x width))]
    (swap! grid assoc idx new-value)))

(defn toggle-cell [x y]
  (let [cell-state (get-cell x y)]
    (set-cell x y (if (zero? cell-state) 1 0))))

(defn setup []
  (createCanvas)
  (js/frameRate 10)
  (doseq [i (range 30)]
    (set-cell (+ 10 (rand-int 15)) (+ 10 (rand-int 15)) 1))
  (js/noStroke)
  (js/colorMode js/HSL 100))

(defn drawcube [x y state]
  (let [color (* 100 (- 1 state))]
    (js/push)
    (js/translate (* cell-size x) (* cell-size y))
    (js/fill 0 0 color)
    (js/rect 0 0 cell-size cell-size)
    (js/pop)))

(defn update-life []
  (reset! grid (into [] (for [y (range height) x (range width)]
                 (let [cell (get-cell x y)
                       surrounding (+ (get-cell (dec x) (dec y))
                                      (get-cell x (dec y))
                                      (get-cell (inc x) (dec y))
                                      (get-cell (dec x) y)
                                      (get-cell (inc x) y)
                                      (get-cell (dec x) (inc y))
                                      (get-cell x (inc y))
                                      (get-cell (inc x) (inc y)))]
                   (cond
                     (and (not (zero? cell)) (< surrounding 2)) 0
                     (and (zero? cell) (= surrounding 3)) 1
                     (and (not (zero? cell)) (> surrounding 3)) 0
                     :else cell
                     ))))))

(defn draw []
  (doseq [x (range width) y (range height)]
    (drawcube x y (get-cell x y)))
  (when (not @editmode) (update-life)))

(defn mousePressed []
  (toggle-cell (quot js/mouseX cell-size)
               (quot js/mouseY cell-size)))

(defn keyPressed []
  (swap! editmode not))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw)
  (g/set "keyPressed" keyPressed)
  (g/set "mousePressed" mousePressed)
  (g/set "windowResized" createCanvas))



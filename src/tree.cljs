
(ns tree
  "recursively render a tree"
  (:require
    [goog.object :as g]
    [p5 :as p5]))

(defn createCanvas []
  (js/createCanvas js/window.innerWidth js/window.innerHeight))

(defn create-tree [level]
  (if (< level 7)
    (let [branches (range (inc (rand-int 3)))]
      (map (fn []
             [(- 1 (* 2 (rand))) (create-tree (inc level))]
             ) branches))
    nil))

(defn setup []
  (createCanvas)
  (def tree (atom [0 (create-tree 0)]))
  (js/noStroke)
  (js/colorMode js/HSL 100))

(defn draw-tree [node]
  (when-let [[r nodes] node]
    (doseq [n nodes]
      (js/push)
        (js/scale 0.8)
        (js/rotate (* r js/PI 0.5))
        (js/fill 40 90 40)
        (js/circle 0 -80 80)
        (js/fill 10 90 40)
        (js/rect -10 -80 20 80)
        (js/push)
          (js/translate 0 -80)
          (draw-tree n)
        (js/pop)
      (js/pop))))

(defn draw []
  (js/translate (/ js/window.innerWidth 2) (- js/window.innerHeight 100))
  (js/background 55 100 80)
  (js/fill 40 90 40)
  (js/circle 0 390 800)
  (draw-tree @tree))

(defn ^:dev/after-load start []
  (js/console.log "start"))

(defn mousePressed []
  (js/clear)
  (reset! tree [0 (create-tree 0)])
  (if (js/isLooping) (js/noLoop) (js/loop)))

(doto js/window
  (g/set "setup" setup)
  (g/set "draw" draw)
  (g/set "mousePressed" mousePressed)
  (g/set "windowResized" createCanvas))



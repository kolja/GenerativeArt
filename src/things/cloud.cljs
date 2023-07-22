(ns things.cloud)

(defn draw [x y]
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

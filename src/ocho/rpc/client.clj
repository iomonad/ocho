(ns ocho.rpc.client
  (:require [ocho.rpc.commons :refer :all]
            [langohr.core :as lc]
            [langohr.channel :as lch]
            [langohr.queue :as lq]
            [langohr.basic :as lb]
            [langohr.consumers :as lcons]))

(defrecord RpcClient [conn ch cbq consumer]
  clojure.lang.IFn
  (invoke [this n]
    (let [correlation-id (gen-corrid)]
      (lb/publish ch "" q (str n) {:reply-to cbq
                                   :correlation-id correlation-id})
      (lb/consume ch cbq consumer)
      (-> (first (filter (partial correlation-id-equals? correlation-id)
                         (lcons/deliveries-seq consumer)))
          .getBody
          (String. "UTF-8")
          (read-string))))
  java.io.Closeable
  (close [this]
    (.close conn)))

(defn create-rpc-client []
  "TODO"
  (let [conn     (lc/connect)
        ch       (lch/open conn)
        cbq      (lq/declare ch "" {:auto-delete false :exclusive true})
        consumer (lcons/create-queueing ch {})]
        (->RpcClient conn ch (:queue cbq) consumer)))

(defn rpc-invoke
  "Invoke function and arg in running server namespace
   and return response"
  [fn-name args & {:keys [host work-queue]}]
  (with-open [c (crea)]))

(rpc-invoke "nancy" 1)

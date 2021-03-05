(ns ocho.rpc.commons)

(defn gen-corrid []
  "Correlation ID for CQRS"
  (str (java.util.UUID/randomUUID)))

(defn correlation-id-equals?
  "Find correct corr id in response payload"
  [correlation-id d]
  (= (.getCorrelationId (.getProperties d)) correlation-id))

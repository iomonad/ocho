(ns ocho.rpc.server)

(defn ^:private local-invoke [^String nm & args]
  "Invoke function in local rpc namespace"
  (when-let [fun (ns-resolve *ns* (symbol nm))]
    (try
      (apply fun args)
      (catch Exception e
        {:error (.getMessage e)}))))

(defn rpc-server [work-queue])

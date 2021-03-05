(defproject ocho "0.0.1"
  :description "General purpose toolkit for micro-service communication and composition on the Clojure ecosystem."
  :url "https://github.com/iomonad/ocho"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.novemberain/langohr "5.1.0"]]
  :repl-options {:init-ns ocho.core})

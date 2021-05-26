Simple uberjar with datahike

```
clj -M -m run
# logging ommitted
#{[4 "Bob" 30] [5 "Charlie" 40] [3 "Alice" 20]}

clojure -X:uberjar :jar MyProject.jar

java -cp MyProject.jar clojure.main -m run
# logging ommitted
#{[4 "Bob" 30] [5 "Charlie" 40] [3 "Alice" 20]}
```

# priority-queue

A Java Priority Queue implementation with O(log n) time complexity for remove(T). Useful if you need to hold many items at once.

## Example
```java
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    queue.offer(1);
    queue.offer(2);

    if (!queue.isEmpty()) {
        queue.remove(2);
    }

    if (queue.peek() == 1) {
        int one = queue.poll();
    }

    if (queue.size() > 0) {
        queue.clear();
    }
    
    List<Integer> heap = queue.getHeap();
```

## Benchmarks
The following benchmarks were performed on a MBP 2013 with JMH using the benchmark suite found in the `benchmarks` directory.

### Java
```
Benchmark      (distribution)     (n)  Mode      Score   Units
JavaPQ.remove          RANDOM       1    ss      0.025   ms/op
JavaPQ.remove          RANDOM     100    ss      0.394   ms/op
JavaPQ.remove          RANDOM    1000    ss      5.266   ms/op
JavaPQ.remove          RANDOM   10000    ss     48.121   ms/op
JavaPQ.remove          RANDOM  100000    ss   3891.546   ms/op
JavaPQ.remove      INCREASING       1    ss      0.031   ms/op
JavaPQ.remove      INCREASING     100    ss      0.631   ms/op
JavaPQ.remove      INCREASING    1000    ss      4.525   ms/op
JavaPQ.remove      INCREASING   10000    ss      9.039   ms/op
JavaPQ.remove      INCREASING  100000    ss     61.324   ms/op
JavaPQ.remove      DECREASING       1    ss      0.029   ms/op
JavaPQ.remove      DECREASING     100    ss      0.444   ms/op
JavaPQ.remove      DECREASING    1000    ss      2.013   ms/op
JavaPQ.remove      DECREASING   10000    ss      5.803   ms/op
JavaPQ.remove      DECREASING  100000    ss     49.606   ms/op
```

### This
```
Benchmark      (distribution)     (n)  Mode    Score   Units
OurPQ.remove           RANDOM       1    ss    0.032   ms/op
OurPQ.remove           RANDOM     100    ss    0.790   ms/op
OurPQ.remove           RANDOM    1000    ss    3.711   ms/op <----
OurPQ.remove           RANDOM   10000    ss   11.722   ms/op <----
OurPQ.remove           RANDOM  100000    ss   87.991   ms/op <----
OurPQ.remove       INCREASING       1    ss    0.056   ms/op
OurPQ.remove       INCREASING     100    ss    4.247   ms/op
OurPQ.remove       INCREASING    1000    ss   11.582   ms/op
OurPQ.remove       INCREASING   10000    ss   40.605   ms/op
OurPQ.remove       INCREASING  100000    ss  183.372   ms/op
OurPQ.remove       DECREASING       1    ss    0.033   ms/op
OurPQ.remove       DECREASING     100    ss    1.484   ms/op
OurPQ.remove       DECREASING    1000    ss    8.227   ms/op
OurPQ.remove       DECREASING   10000    ss   44.944   ms/op
OurPQ.remove       DECREASING  100000    ss  182.505   ms/op
```
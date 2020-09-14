# priority-queue

A Java Priority Queue implementation with O(log n) time complexity for `remove(T)`.

## Example

```java
    PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);
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
The following benchmarks were performed on a MBP 2013 with JMH using the benchmark suite found in the `benchmarks` directory. Score unit is ms/op.

### Java
```
Benchmark      (distribution)     (n)  Mode      Score
JavaPQ.remove          RANDOM       1    ss      0.025
JavaPQ.remove          RANDOM     100    ss      0.394
JavaPQ.remove          RANDOM    1000    ss      5.266
JavaPQ.remove          RANDOM   10000    ss     48.121
JavaPQ.remove          RANDOM  100000    ss   3891.546
JavaPQ.remove      INCREASING       1    ss      0.031
JavaPQ.remove      INCREASING     100    ss      0.631
JavaPQ.remove      INCREASING    1000    ss      4.525
JavaPQ.remove      INCREASING   10000    ss      9.039
JavaPQ.remove      INCREASING  100000    ss     61.324
JavaPQ.remove      DECREASING       1    ss      0.029
JavaPQ.remove      DECREASING     100    ss      0.444
JavaPQ.remove      DECREASING    1000    ss      2.013
JavaPQ.remove      DECREASING   10000    ss      5.803
JavaPQ.remove      DECREASING  100000    ss     49.606
```

### This
```
Benchmark     (distribution)     (n)  Mode     Score
OurPQ.remove          RANDOM       1    ss     0.022 <----
OurPQ.remove          RANDOM     100    ss     0.501
OurPQ.remove          RANDOM    1000    ss     4.209 <----
OurPQ.remove          RANDOM   10000    ss    13.317 <----
OurPQ.remove          RANDOM  100000    ss    69.880 <----
OurPQ.remove      INCREASING       1    ss     0.039
OurPQ.remove      INCREASING     100    ss     2.278
OurPQ.remove      INCREASING    1000    ss    16.019
OurPQ.remove      INCREASING   10000    ss    42.658
OurPQ.remove      INCREASING  100000    ss   140.316
OurPQ.remove      DECREASING       1    ss     0.036
OurPQ.remove      DECREASING     100    ss     1.731
OurPQ.remove      DECREASING    1000    ss     8.465
OurPQ.remove      DECREASING   10000    ss    34.368
OurPQ.remove      DECREASING  100000    ss   144.530
```
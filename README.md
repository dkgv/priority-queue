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

```
(item order)      (n)  Mode   Java.remove   This.remove
      RANDOM        1    ss         0.025         0.022 <----
      RANDOM      100    ss         0.394         0.501
      RANDOM     1000    ss         5.266         4.209 <----
      RANDOM    10000    ss        48.121        13.317 <----
      RANDOM   100000    ss      3891.546        69.880 <----
  INCREASING        1    ss         0.031         0.039
  INCREASING      100    ss         0.631         2.278
  INCREASING     1000    ss         4.525        16.019
  INCREASING    10000    ss         9.039        42.658
  INCREASING   100000    ss        61.324       140.316
  DECREASING        1    ss         0.029         0.036
  DECREASING      100    ss         0.444         1.731
  DECREASING     1000    ss         2.013         8.465
  DECREASING    10000    ss         5.803        34.368
  DECREASING   100000    ss        49.606       144.530
```

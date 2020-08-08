# priority-queue

A Java Priority Queue implementation with O(log n) time complexity for remove(T).

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

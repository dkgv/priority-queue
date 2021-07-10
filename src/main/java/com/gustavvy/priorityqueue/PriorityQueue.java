package com.gustavvy.priorityqueue;

import java.util.*;

/**
 * PriorityQueue.java
 *
 * @author Gustav V. Y.
 */
public class PriorityQueue<T> {

	private final HashMap<T, Integer> indices;
	private final Comparator<T> comparator;
	private final ArrayList<T> heap;

	public PriorityQueue(final Comparator<T> comparator) {
		this(11, comparator);
	}

	public PriorityQueue(final int initialCapacity, final Comparator<T> comparator) {
		this.indices = new HashMap<>(initialCapacity);
		this.heap = new ArrayList<>(initialCapacity);
		this.comparator = comparator;
	}

	public void clear() {
		indices.clear();
		heap.clear();
	}

	public T poll() {
		return removeAt(0);
	}

	private T removeAt(final int index) {
		final T result = heap.get(index);
		final int lastIndex = heap.size() - 1;

		swap(index, lastIndex);

		indices.remove(result);
		heap.remove(lastIndex);
		minHeapify(index);

		return result;
	}

	private void swap(final int i, final int j) {
		final T tmp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, tmp);
		indices.put(tmp, j);
		indices.put(heap.get(i), i);
	}

	public List<T> getHeap() {
		return heap;
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public void remove(final T entry) {
		if (!indices.containsKey(entry)) {
			return;
		}

		final int lastIndex = heap.size() - 1;
		final int entryIndex = indices.get(entry);

		// Is entry the last element? Remove and we are done (think heap visually)
		if (entryIndex >= lastIndex) {
			heap.remove(lastIndex);
			indices.remove(entry);
		} else {
			removeAt(entryIndex);
		}
	}

	public boolean offer(final Collection<T> collection) {
		for (final T t : collection) {
			if (!offer(t)) {
				return false;
			}
		}
		return true;
	}

	public boolean offer(final T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		heap.add(t);
		final int last = heap.size() - 1;
		indices.put(t, last);
		siphonUp(last);
		return true;
	}

	public T peek() {
		return heap.get(0);
	}

	public boolean contains(final T t) {
		return indices.containsKey(t);
	}

	public int size() {
		return heap.size();
	}

	private void siphonUp(int index) {
		// Continue swapping until we reach the root as long as comparison with our parent is met
		while (index > 0 && cmp(heap.get(index), heap.get(parentOf(index))) < 0) {
			swap(index, parentOf(index));
			index = parentOf(index);
		}
	}

	private void minHeapify(final int index) {
		final int shift = index << 1;
		final int left = shift + 1;
		final int right = shift + 2;
		final int n = heap.size();

		int min = index;

		// Ensure a left child exists and compare index with it
		if (left < n && cmp(heap.get(left), heap.get(index)) < 0) {
			min = left;
		}

		if (right < n && cmp(heap.get(right), heap.get(min)) < 0) {
			min = right;
		}

		if (min != index) {
			swap(index, min);
			minHeapify(min);
		}
	}

	private int cmp(final T one, final T two) {
		return comparator.compare(one, two);
	}

	private int parentOf(final int index) {
		return (index - 1) / 2;
	}
}

package com.gustavvy.priorityqueue;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * PriorityQueueTest.java
 *
 * @author Gustav V. Y.
 */
public class PriorityQueueTest {

	private static final Comparator<Integer> ASC = Integer::compareTo;
	private static final Comparator<Integer> DESC = Comparator.reverseOrder();

	@Test
	public void testEmptyOnInit() {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(ASC);
		Assert.assertTrue(priorityQueue.isEmpty());
	}

	@Test
	public void testOfferCollection() {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(ASC);
		Integer[] array = {1, 2, 3};
		priorityQueue.offer(Arrays.asList(array));
		assertCorrectHeapOrder(priorityQueue, new Integer[]{ 1, 2, 3 });
	}

	@Test(expected = NullPointerException.class)
	public void testCantOfferNull() {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(ASC);
		Integer i = null;
		priorityQueue.offer(i);
	}

	@Test
	public void testRemoveCorrectSpecific() {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(ASC);
		priorityQueue.offer(1);
		priorityQueue.offer(2);
		priorityQueue.offer(3);
		priorityQueue.remove(2);
		Assert.assertFalse(priorityQueue.contains(2));
		assertCorrectHeapOrder(priorityQueue, new Integer[]{ 1, 3 });
	}

	@Test
	public void testClear() {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(ASC);
		priorityQueue.offer(1);
		priorityQueue.clear();
		Assert.assertTrue(priorityQueue.isEmpty());
	}

	@Test
	public void testCorrectHeapOrderAfterOfferMin() {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(ASC);
		for (int i = 4; i > 0; i--) {
			priorityQueue.offer(i);
		}
		assertCorrectHeapOrder(priorityQueue, new Integer[]{ 1, 2, 3, 4 });
	}

	@Test
	public void testCorrectHeapOrderAfterOfferMax() {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(DESC);
		for (int i = 1; i < 5; i++) {
			priorityQueue.offer(i);
		}
		Assert.assertArrayEquals(new Integer[]{ 4, 3, 2, 1 }, priorityQueue.getHeap().toArray(new Integer[0]));
	}

	@Test
	public void testCorrectHeapOrderAfterRemove() {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(ASC);
		for (int i = 1; i < 5; i++) {
			priorityQueue.offer(i);
		}
		priorityQueue.remove(3);
		Assert.assertArrayEquals(new Integer[]{ 1, 2, 4 }, priorityQueue.getHeap().toArray(new Integer[0]));
	}

	@Test
	public void testPollCorrectMinInts() {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(ASC);
		priorityQueue.offer(1);
		priorityQueue.offer(1);
		priorityQueue.offer(2);
		priorityQueue.offer(20);
		Assert.assertEquals(1, (int) priorityQueue.poll());
		Assert.assertEquals(1, (int) priorityQueue.poll());
		Assert.assertEquals(2, (int) priorityQueue.poll());
		Assert.assertEquals(20, (int) priorityQueue.poll());
	}

	@Test
	public void testPollCorrectMaxInts() {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(DESC);
		priorityQueue.offer(1);
		priorityQueue.offer(1);
		priorityQueue.offer(2);
		priorityQueue.offer(20);
		Assert.assertEquals(20, (int) priorityQueue.poll());
		Assert.assertEquals(2, (int) priorityQueue.poll());
		Assert.assertEquals(1, (int) priorityQueue.poll());
		Assert.assertEquals(1, (int) priorityQueue.poll());
	}

	@Test
	public void testCorrectOrderObjectsSamePriority() {
		PriorityQueue<Sample> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.priority));
		Sample sample1 = new Sample(0, 10);
		Sample sample2 = new Sample(1, 20);
		Sample sample3 = new Sample(2, 20);
		Sample sample4 = new Sample(3, 20);
		Sample sample5 = new Sample(4, 40);
		priorityQueue.offer(new ArrayList<>(Arrays.asList(sample1, sample2, sample3, sample4, sample5)));
		assertCorrectHeapOrder(priorityQueue, new Sample[]{ sample1, sample2, sample3, sample4, sample5 });
	}

	private <T> void assertCorrectHeapOrder(PriorityQueue<T> queue, T[] order) {
		Assert.assertEquals(queue.size(), order.length);
		for (int i = 0; i < queue.getHeap().size(); i++) {
			Assert.assertEquals(queue.getHeap().get(i), order[i]);
		}
	}

	private static class Sample {

		public int id;
		public int priority;

		public Sample(int id, int priority) {
			this.id = id;
			this.priority = priority;
		}

		@Override
		public String toString() {
			return "Sample{" +
					"id=" + id +
					", priority=" + priority +
					'}';
		}
	}
}

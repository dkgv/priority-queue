package com.gustavvy.priorityqueue;

import org.junit.Assert;
import org.junit.Test;

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
		Assert.assertArrayEquals(new Integer[]{ 1, 2, 3 }, priorityQueue.getHeap().toArray(new Integer[0]));
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
		Assert.assertArrayEquals(new Integer[]{ 1, 2, 3, 4 }, priorityQueue.getHeap().toArray(new Integer[0]));
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
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(DESC);
		priorityQueue.offer(1);
		priorityQueue.offer(1);
		priorityQueue.offer(2);
		priorityQueue.offer(20);
		Assert.assertEquals(20, (int) priorityQueue.poll());
		Assert.assertEquals(2, (int) priorityQueue.poll());
		Assert.assertEquals(1, (int) priorityQueue.poll());
		Assert.assertEquals(1, (int) priorityQueue.poll());
	}
}

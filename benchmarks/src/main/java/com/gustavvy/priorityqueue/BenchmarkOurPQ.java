package com.gustavvy.priorityqueue;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * BenchmarkOurPQ.java
 *
 * @author Gustav V. Y.
 */
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G"})
public class BenchmarkOurPQ {

	@Param({"1", "100", "1000", "10000", "100000"})
	private int n;

	@Param
	private Distribution distribution;

	private int[] values;
	private PriorityQueue<Integer> priorityQueue;

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(BenchmarkOurPQ.class.getSimpleName())
				.forks(1)
				.build();

		new Runner(opt).run();
	}

	@Setup
	public void setup() {
		priorityQueue = new PriorityQueue<>(n, new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				return Integer.compare(x, y);
			}
		});
		values = distribution.generate(n);
		for (int value : values) {
			priorityQueue.offer(value);
		}
	}

	@Benchmark
	public void remove() {
		for (int value : values) {
			priorityQueue.remove(value);
		}
	}
}

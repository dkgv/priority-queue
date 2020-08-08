import org.openjdk.jmh.annotations.Benchmark;

import java.util.PriorityQueue;

/**
 * JavaPriorityQueueBenchmark.java
 *
 * @author Gustav V. Y.
 */
public class JavaPriorityQueueBenchmark {

	@Param({"1", "10", "100", "1000", "10000", "100000", "1000000"})
	private int n;

	@Param
	private BenchmarkBase.Distribution distribution;

	private int[] values;
	private BenchmarkBase benchmarkBase = new BenchmarkBase();
	private PriorityQueue<Integer> priorityQueue;

	public static void main(String[] args) {
		CaliperMain.main(JavaPriorityQueueBenchmark.class, args);
	}

	@BeforeExperiment
	public void setup() {
		values = distribution.generate(n);
		priorityQueue = new PriorityQueue<>();
	}

	@Benchmark
	public void offer() {
		for (int value : values) {
			priorityQueue.offer(value);
		}
	}

	@Benchmark
	public void pollAll() {
		while (!priorityQueue.isEmpty()) {
			priorityQueue.poll();
		}
	}
}

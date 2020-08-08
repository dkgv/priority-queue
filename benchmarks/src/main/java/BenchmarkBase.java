import com.google.caliper.Param;

import java.util.Random;

/**
 * BenchmarkBase.java
 *
 * @author Gustav V. Y.
 */
public class BenchmarkBase {

	public enum Distribution {
		STRICTLY_INCREASING {
			@Override
			public int[] generate(int n) {
				int[] array = new int[n];
				for (int i = 0; i < n; i++) {
					array[i] = i;
				}
				return array;
			}
		},
		STRICTLY_DECREASING {
			@Override
			public int[] generate(int n) {
				int[] array = new int[n];
				for (int i = n - 1; i > 0; i--) {
					array[i] = i;
				}
				return array;
			}
		},
		RANDOM {
			@Override
			public int[] generate(int n) {
				int[] array = new int[n];
				Random random = new Random();
				int i = 0;
				while (i < n) {
					array[i++] = random.nextInt(n);
				}
				return array;
			}
		};

		public abstract int[] generate(int n);
	}
}

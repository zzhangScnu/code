package base;

/**
 * @author lihua
 * @since 2021/10/14
 */
public class IsPowerOfTwo {

	public static boolean isPowerOfTwo(int n) {
		return n > 0
				&& (n & (n - 1)) == 0;
	}
}

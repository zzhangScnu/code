/**
 * @author lihua
 * @since 2021/10/14
 */
public class IsPowerOfTwo {

	public static boolean isPowerOfTwo(int n) {
		if (n <= 0) {
			return false;
		}
		if (n == 1) {
			return true;
		}
		boolean flag = true;
		do {
			if (n % 2 != 0) {
				flag = false;
				break;
			}
			n = n >> 1;
		} while (n != 1);
		return flag;
	}
}

package prefixsum;//给你一个整数数组 nums 和一个整数 k。
//
// 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
//
// 请返回这个数组中「优美子数组」的数目。
//
//
//
// 示例 1：
//
// 输入：nums = [1,1,2,1,1], k = 3
//输出：2
//解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
//
//
// 示例 2：
//
// 输入：nums = [2,4,6], k = 1
//输出：0
//解释：数列中不包含任何奇数，所以不存在优美子数组。
//
//
// 示例 3：
//
// 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
//输出：16
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 50000
// 1 <= nums[i] <= 10^5
// 1 <= k <= nums.length
//
// Related Topics 数组 哈希表 数学 滑动窗口

import java.util.HashMap;
import java.util.Map;

/**
 * todo: 优化
 * v1：记录每个数字出现多少次，或是其他与数字有关的属性，可以用int[]代替map
 * v2：可以边计算前缀和边计算次数
 *
 * @author lihua
 * @since 2021/10/19
 */
public class NumberOfSubarrays {

	public static int numberOfSubarrays(int[] nums, int k) {
		int length = nums.length;
		// sums数组的i，表示前i-1位有多少个奇数
		int[] sums = new int[length + 1];
		sums[0] = 0;
		for (int i = 0; i < length; i++) {
			if (nums[i] % 2 != 0) {
				sums[i + 1] = sums[i] + 1;
			} else {
				sums[i + 1] = sums[i];
			}
		}
		int result = 0;
		// 前缀和值 -> 出现的次数
		Map<Integer, Integer> sumCountMap = new HashMap<>();
		for (int i = 0; i < length + 1; i++) {
			// 由sums[i] - sums[j] == k 推出 sums[i] - k == sums[j]
			if (sumCountMap.containsKey(sums[i] - k)) {
				// 是加上map中记录的出现次数，不是直接加1
				result += sumCountMap.get(sums[i] - k);
			}
			sumCountMap.put(sums[i], sumCountMap.getOrDefault(sums[i], 0) + 1);
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
		int k = 2;
		int result = numberOfSubarrays(nums, k);
		assert result == 16;
	}
}

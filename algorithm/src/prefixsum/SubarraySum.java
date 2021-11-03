package prefixsum;//给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,1], k = 2
//输出：2
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3], k = 3
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2 * 104
// -1000 <= nums[i] <= 1000
// -107 <= k <= 107
//
// Related Topics 数组 哈希表 前缀和

import java.util.HashMap;
import java.util.Map;

/**
 * @author lihua
 * @since 2021/10/18
 */
public class SubarraySum {

	/**
	 * 求连续子数组 -> nums是排好序的数组
	 * 用前缀和求解，有点像滑动窗口
	 * sum[n]是0..n（即前n个数）的和
	 */
	public int subarraySum(int[] nums, int k) {
		int count = 0;
		int length = nums.length;
		int[] sums = new int[length];
		// 前缀和数组赋值
		sums[0] = nums[0];
		for (int i = 1; i < length; i++) {
			sums[i] = sums[i - 1] + nums[i];
		}
		// 前缀和 -> 出现次数
		Map<Integer, Integer> sumMap = new HashMap<>();
		// 刚好是前n位，没有前缀和的情况，比如[1, 1, 1, 2], k = 3
		sumMap.put(0, 1);
		int sum;
		for (int i = 0; i < length; i++) {
			sum = sums[i] - k;
			if (sumMap.containsKey(sum)) {
				count += sumMap.get(sum);
			}
			sumMap.put(sums[i], sumMap.getOrDefault(sums[i], 0) + 1);
		}
		return count;
	}
}

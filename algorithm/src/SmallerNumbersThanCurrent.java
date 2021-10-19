//给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
//
// 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
//
// 以数组形式返回答案。
//
//
//
// 示例 1：
//
// 输入：nums = [8,1,2,2,3]
//输出：[4,0,1,1,3]
//解释：
//对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
//对于 nums[1]=1 不存在比它小的数字。
//对于 nums[2]=2 存在一个比它小的数字：（1）。
//对于 nums[3]=2 存在一个比它小的数字：（1）。
//对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
//
//
// 示例 2：
//
// 输入：nums = [6,5,4,8]
//输出：[2,1,0,3]
//
//
// 示例 3：
//
// 输入：nums = [7,7,7,7]
//输出：[0,0,0,0]
//
//
//
//
// 提示：
//
//
// 2 <= nums.length <= 500
// 0 <= nums[i] <= 100
//
// Related Topics 数组 哈希表 计数 排序

/**
 * @author lihua
 * @since 2021/10/18
 */
public class SmallerNumbersThanCurrent {

	public static int[] smallerNumbersThanCurrent(int[] nums) {
		// 注意在数据准备阶段，都是以题目给的最大数字数量作为上限
		int maxNumLength = 101;
		int[] counter = new int[maxNumLength];
		// 统计各个数字出现多少次
		for (int num : nums) {
			counter[num]++;
		}
		// 统计各个数字，比它小的数字有多少个
		int[] sums = new int[maxNumLength];
		sums[0] = 0;
		for (int i = 1; i < maxNumLength; i++) {
			sums[i] = sums[i - 1] + counter[i - 1];
		}
		int resultLength = nums.length;
		int[] results = new int[resultLength];
		for (int i = 0; i < resultLength; i++) {
			// 按nums的顺序输出sum
			results[i] = sums[nums[i]];
		}
		return results;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{8, 1, 2, 2, 3};
		int[] results = smallerNumbersThanCurrent(nums);
		// 4, 0, 1, 1, 3
		assert results.length == 5;
	}
}

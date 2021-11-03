package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 15-三数之和
// 3sum
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
//
//
// 示例 1：
//
//
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：nums = [0]
//输出：[]
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 3000
// -105 <= nums[i] <= 105
//
// Related Topics 数组 双指针 排序

/**
 * @author lihua
 * @since 2021/10/14
 */
public class ThreeSum {

	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> resultList = new ArrayList<>();
		Arrays.sort(nums);
		int length = nums.length;
		int third;
		for (int first = 0; first < length; first++) {
			// 避免空指针
			// 每一轮枚举的元素，不能跟上一轮的一样，避免在nums有连续重复元素时，结果出现重复元组
			if (first != 0 && nums[first] == nums[first - 1]) {
				continue;
			}
			third = length - 1;
			for (int second = first + 1; second < length; second++) {
				// 虽然结果集里面不能有重复的元组，但是每个元组里面的元素还是可以存在相同的
				// 所以这里排除掉second == first + 1的情况。避免取不到第一个second和本轮first指向的元素相同的情况
				if (second != first + 1 && nums[second] == nums[second - 1]) {
					continue;
				}
				while (second < third && nums[first] + nums[second] + nums[third] > 0) {
					third--;
				}
				if (second == third) {
					break;
				}
				if (nums[first] + nums[second] + nums[third] == 0) {
					fillResult(first, second, third, nums, resultList);
				}
			}
		}
		return resultList;
	}

	private static void fillResult(int first, int second, int third, int[] nums, List<List<Integer>> resultList) {
		List<Integer> result = Arrays.asList(nums[first], nums[second], nums[third]);
		resultList.add(result);
	}

	public static void main(String[] args) {
		int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
		List<List<Integer>> resultList = threeSum(nums);
		assert resultList.size() == 2;
	}
}

package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 18-四数之和
// 4sum
//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b
//], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
//
//
// 0 <= a, b, c, d < n
// a、b、c 和 d 互不相同
// nums[a] + nums[b] + nums[c] + nums[d] == target
//
//
// 你可以按 任意顺序 返回答案 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 200
// -109 <= nums[i] <= 109
// -109 <= target <= 109
//
// Related Topics 数组 双指针 排序

/**
 * @author lihua
 * @since 2021/10/15
 */
public class NSum {

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		return nSum(nums, target, 0, 4);
	}

	/**
	 * @param nums   原始数组
	 * @param target 目标值
	 * @param from   遍历nums数组的起始index
	 * @param n      nSum的轮数
	 */
	private static List<List<Integer>> nSum(int[] nums, int target, int from, int n) {
		List<List<Integer>> resultList = new ArrayList<>();
		int length = nums.length;
		if (n < 2 || length < n) {
			return resultList;
		}
		if (n == 2) {
			return twoSum(nums, from, target);
		}
		// length, not n
		for (int first = from; first < length; first++) {
			// 这里一定要赋值给额外的变量，因为lambda要求要用efficiency final形式
			int firstNum = nums[first];
			// 这里第一次写成了target -= firstNum
			int newTarget = target - firstNum;
			// first, not from
			nSum(nums, newTarget, first + 1, n - 1).stream()
					.peek(result -> result.add(firstNum))
					.forEach(resultList::add);
			// 防止出现重复元组
			while (first < length - 1 && nums[first] == nums[first + 1]) {
				first++;
			}
		}
		return resultList;
	}

	/**
	 * 左右指针，两数之和
	 */
	private static List<List<Integer>> twoSum(int[] nums, int from, int target) {
		List<List<Integer>> resultList = new ArrayList<>();
		int low = from;
		int high = nums.length - 1;
		while (low < high) {
			int lowNum = nums[low];
			int highNum = nums[high];
			int sum = lowNum + highNum;
			if (sum > target) {
				high--;
			}
			// 这里if和else if等价
			if (sum < target) {
				low++;
			}
			if (sum == target) {
				// 因为到上层还要把firstNum加进来，所以不能直接用Arrays.asList
				List<Integer> result = new ArrayList<>();
				result.add(nums[low]);
				result.add(nums[high]);
				resultList.add(result);
				// 注意界限校验，跳过重复元素
				while (low < high && nums[low] == lowNum) {
					low++;
				}
				while (low < high && nums[high] == highNum) {
					high--;
				}
			}
		}
		return resultList;
	}

	public static void main(String[] args) {
		int target = 0;
		int[] nums = new int[]{1, 0, -1, 0, -2, 2};
		List<List<Integer>> resultList = fourSum(nums, target);
		assert resultList.size() == 3;
		target = 8;
		nums = new int[]{2, 2, 2, 2};
		resultList = fourSum(nums, target);
		assert resultList.size() == 1;
		target = 0;
		nums = new int[]{0, 0};
		resultList = fourSum(nums, target);
		assert resultList.isEmpty();
		target = 1;
		nums = new int[]{0, 0, 0, 0};
		resultList = fourSum(nums, target);
		assert resultList.isEmpty();
	}
}

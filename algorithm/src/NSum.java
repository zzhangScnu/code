import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lihua
 * @since 2021/10/15
 */
public class NSum {

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		if (nums.length < 4) {
			return new ArrayList<>();
		}
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
		if (n == 2) {
			return twoSum(nums, from, target);
		}
		int length = nums.length;
		List<List<Integer>> resultList = new ArrayList<>();
		for (int first = from; first < length; first++) {
			// 防止出现重复元组
			while (first != from && first < length - 1 && nums[first] == nums[first - 1]) {
				first++;
			}
			// 如果检查到最后，都没有符合条件的元素，就跳过这一轮了
			if (first == length - 1) {
				continue;
			}
			int firstNum = nums[first];
			int newTarget = target - firstNum;
			nSum(nums, newTarget, first + 1, n - 1).stream()
					.peek(result -> result.add(firstNum))
					.forEach(resultList::add);
		}
		return resultList;
	}

	/**
	 * 左右指针，两数之和
	 */
	private static List<List<Integer>> twoSum(int[] nums, int from, int target) {
		List<List<Integer>> resultList = new ArrayList<>();
		int length = nums.length;
		int low = from;
		int high = length - 1;
		while (low < high) {
			if (nums[low] + nums[high] > target) {
				high--;
				// 要注意界限校验，而且这个界限是以另外一个指针做标准的，不是数组上下界
				while (high - 1 > low && nums[high] == nums[high - 1]) {
					high--;
				}
			}
			if (nums[low] + nums[high] < target) {
				low++;
				while (low + 1 < high && nums[low] == nums[low + 1]) {
					low++;
				}
			}
			if (low < high && nums[low] + nums[high] == target) {
				List<Integer> result = new ArrayList<>();
				result.add(nums[low]);
				result.add(nums[high]);
				resultList.add(result);
				low++;
				high--;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		for (int first = from; first < length; first++) {
			int firstNum = nums[first];
			int newTarget = target - firstNum;
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
			if (sum < target) {
				low++;
			}
			if (sum == target) {
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

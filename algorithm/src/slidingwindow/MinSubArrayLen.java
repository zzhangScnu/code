package slidingwindow;
// 209-长度最小的子数组
// minimum-size-subarray-sum
//给定一个含有 n 个正整数的数组和一个正整数 target 。
//
// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长
//度。如果不存在符合条件的子数组，返回 0 。
//
//
//
// 示例 1：
//
//
//输入：target = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
//
//
// 示例 2：
//
//
//输入：target = 4, nums = [1,4,4]
//输出：1
//
//
// 示例 3：
//
//
//输入：target = 11, nums = [1,1,1,1,1,1,1,1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= target <= 109
// 1 <= nums.length <= 105
// 1 <= nums[i] <= 105
//
//
//
//
// 进阶：
//
//
// 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
//
// Related Topics 数组 二分查找 前缀和 滑动窗口

/**
 * @author lihua
 * @since 2021/11/8
 */
public class MinSubArrayLen {

    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int left = 0, right = 0, sum = 0;
        int length = nums.length;
        while (right < length) {
            sum = sum + nums[right];
            right++;
            while (sum >= target) {
                minLength = Math.min(right - left, minLength);
                sum = sum - nums[left];
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        MinSubArrayLen clazz = new MinSubArrayLen();
        int target = 7;
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int result = clazz.minSubArrayLen(target, nums);
        assert result == 2;
    }
}

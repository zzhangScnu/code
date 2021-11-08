package slidingwindow;
// 643-子数组最大平均数 I
// maximum-average-subarray-i
//给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
//
// 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
//
// 任何误差小于 10-5 的答案都将被视为正确答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,12,-5,-6,50,3], k = 4
//输出：12.75
//解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
//
//
// 示例 2：
//
//
//输入：nums = [5], k = 1
//输出：5.00000
//
//
//
//
// 提示：
//
//
// n == nums.length
// 1 <= k <= n <= 105
// -104 <= nums[i] <= 104
//
// Related Topics 数组 滑动窗口

/**
 * @author lihua
 * @since 2021/11/8
 */
public class FindMaxAverage {

    public double findMaxAverage(int[] nums, int k) {
        // 如果定义为0，则无法解决最大值为负数的情况
        int maxSum = Integer.MIN_VALUE, maxCount = Integer.MIN_VALUE;
        int sum = 0, count = 0;
        int left = 0, right = 0;
        int length = nums.length;
        while (right < length) {
            sum += nums[right];
            count++;
            right++;
            // 固定窗口长度，当超过窗口长度的时候收缩上界
            while (right - left >= k) {
                // 当等于窗口长度的时候更新结果
                if (right - left == k) {
                    // 如果判断条件是小于，则无法解决maxCount最后为0(没有更新过)的情况
                    if (maxSum <= sum) {
                        maxSum = sum;
                        maxCount = count;
                    }
                }
                sum = sum - nums[left];
                count--;
                left++;
            }
        }
        return maxSum / (double) maxCount;
    }

    public static void main(String[] args) {
        FindMaxAverage clazz = new FindMaxAverage();
        double maxAverage = clazz.findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4);
        assert maxAverage - 12 < 1;
    }
}

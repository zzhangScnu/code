package slidingwindow;
// 1004-最大连续1的个数 III
// max-consecutive-ones-iii
//给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
//
// 返回仅包含 1 的最长（连续）子数组的长度。
//
//
//
// 示例 1：
//
// 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//输出：6
//解释：
//[1,1,1,0,0,1,1,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 6。
//
// 示例 2：
//
// 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//输出：10
//解释：
//[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 10。
//
//
//
// 提示：
//
//
// 1 <= A.length <= 20000
// 0 <= K <= A.length
// A[i] 为 0 或 1
//
// Related Topics 数组 二分查找 前缀和 滑动窗口

/**
 * @author lihua
 * @since 2021/11/9
 */
public class LongestOnes {

    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0;
        int length = nums.length;
        int maxLength = 0;
        int[] count = new int[]{0, 0};
        while (right < length) {
            count[nums[right]]++;
            right++;
            // 由题意得，滑动窗口中0的个数不能大于k个
            while (count[0] > k) {
                if (nums[left] == 0) {
                    count[0]--;
                }
                left++;
            }
            // 当滑动窗口中0的个数小于等于k个的时候，满足题意，更新结果
            maxLength = Math.max(right - left, maxLength);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestOnes clazz = new LongestOnes();
        int result = clazz.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3);
        assert result == 10;
    }
}

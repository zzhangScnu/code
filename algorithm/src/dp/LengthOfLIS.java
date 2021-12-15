package dp;

// 300-最长递增子序列
// longest-increasing-subsequence
//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。
//
//
// 示例 1：
//
//
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
//
//
// 示例 2：
//
//
//输入：nums = [0,1,0,3,2,3]
//输出：4
//
//
// 示例 3：
//
//
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2500
// -104 <= nums[i] <= 104
//
//
//
//
// 进阶：
//
//
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
//
// Related Topics 数组 二分查找 动态规划

/**
 * @author lihua
 * @since 2021/12/14
 */
public class LengthOfLIS {

    public int lengthOfLIS(int[] nums) {
        int maxLength = 1;
        int length = nums.length;
        int[] counter = initialCount(length);
        for (int cur = 1; cur < length; cur++) {
            for (int pre = 0; pre <= cur; pre++) {
                if (nums[pre] < nums[cur]) {
                    counter[cur] = Math.max(counter[cur], counter[pre] + 1);
                    maxLength = Math.max(maxLength, counter[cur]);
                }
            }
        }
        return maxLength;
    }

    /**
     * counter[i]表示[any..i]中，最长递增子序列的长度
     */
    private int[] initialCount(int length) {
        int[] counter = new int[length];
        for (int i = 0; i < length; i++) {
            counter[i] = 1;
        }
        return counter;
    }

    public static void main(String[] args) {
        LengthOfLIS clazz = new LengthOfLIS();
        // 3, 8, 9
        int result = clazz.lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9});
        assert result == 3;
    }
}

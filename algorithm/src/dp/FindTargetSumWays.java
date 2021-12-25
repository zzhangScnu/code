package dp;

// 494-目标和
// target-sum
//给你一个整数数组 nums 和一个整数 target 。
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
//
//
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
//
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
//
//
// 示例 2：
//
//
//输入：nums = [1], target = 1
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 20
// 0 <= nums[i] <= 1000
// 0 <= sum(nums[i]) <= 1000
// -1000 <= target <= 1000
//
// Related Topics 数组 动态规划 回溯

import java.util.HashMap;

/**
 * 中间那段遍历子树(选择-撤销)的过程等同于：
 * // 选择加号
 * target -= nums[index];
 * doFind(nums, target, index + 1);
 * target += nums[index];
 * // 选择减号
 * target += nums[index];
 * doFind(nums, target, index + 1);
 * target -= nums[index];
 *
 * @author lihua
 * @since 2021/12/20
 */
public class FindTargetSumWays {

    private HashMap<String, Integer> resultMap = new HashMap<>();

    public int findTargetSumWays(int[] nums, int target) {
        return doFind(nums, target, 0);
    }

    private int doFind(int[] nums, int target, int index) {
        if (index == nums.length) {
            if (target == 0) {
                return 1;
            }
            return 0;
        }
        String key = target + "/" + index;
        if (resultMap.containsKey(key)) {
            return resultMap.get(key);
        }
        // 其实相当于中序遍历时，左右两棵子树的和
        int subResult1 = doFind(nums, target - nums[index], index + 1);
        int subResult2 = doFind(nums, target + nums[index], index + 1);
        int result = subResult1 + subResult2;
        resultMap.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        FindTargetSumWays clazz = new FindTargetSumWays();
        int[] nums = new int[]{100};
        int target = -200;
        int result = clazz.findTargetSumWays(nums, target);
        assert result == 0;
        // todo：这个用例暂时想不到解
        result = clazz.findTargetSumWaysByDp(nums, target);
        assert result == 0;
    }

    /**
     * 一开始的思路其实是将元素变成正负数作为候选项，来求结果值的
     * 但是这样有个问题，dp[i][w]的w在减去负值的时候就会越界
     * <p>
     * sum(A) - sum(B) = target
     * sum(A) = target + sum(B)
     * sum(A) + sum(A) = target + sum(B) + sum(A)
     * 2 * sum(A) = target + sum(nums)
     * sum(A) = (target + sum(nums)) / 2
     * 问题转化成对子集A，有多少种方法可以凑成
     */
    public int findTargetSumWaysByDp(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 注意边界条件
        if (sum < target || (sum + target) % 2 != 0) {
            return 0;
        }
        return doFindTargetSumWaysByDp(nums, (sum + target) / 2);
    }

    private int doFindTargetSumWaysByDp(int[] nums, int target) {
        int length = nums.length;
        int[][] dp = initialDp(length, target);
        for (int i = 1; i <= length; i++) {
            for (int w = 1; w <= target; w++) {
                if (nums[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 当前的方法数为：装入 + 不装入的方法数的和
                    dp[i][w] = dp[i - 1][w - nums[i - 1]] + dp[i - 1][w];
                }
            }
        }
        return dp[length][target];
    }

    private int[][] initialDp(int length, int target) {
        int[][] dp = new int[length + 1][target + 1];
        for (int i = 0; i <= length; i++) {
            dp[i][0] = 1;
        }
        return dp;
    }
}

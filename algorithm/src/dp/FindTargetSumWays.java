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
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int target = 3;
        int result = clazz.findTargetSumWays(nums, target);
        assert result == 5;
    }
}

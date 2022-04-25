package backtracking;
//给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
//
// 示例 1：
//
// 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
//
//
//
// 提示：
//
//
// 1 <= k <= len(nums) <= 16
// 0 < nums[i] < 10000
//
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩

/**
 * @author lihua
 * @since 2021/10/27
 */
public class CanPartitionKSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 直接不能平均分的
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        int[] buckets = new int[k];
        return backtrack(nums, buckets, 0, target);
    }

    private boolean backtrack(int[] nums, int[] buckets, int numsIndex, int target) {
        // 遍历完了，检查放置情况
        if (nums.length == numsIndex) {
            for (int bucket : buckets) {
                if (bucket != target) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < buckets.length; i++) {
            // 剪枝，大于target的就直接跳过了
            if (buckets[i] + nums[numsIndex] > target) {
                continue;
            }
            buckets[i] += nums[numsIndex];
            // 相当于for的外层循环。所以这里等价于双重循环的
            if (backtrack(nums, buckets, numsIndex + 1, target)) {
                return true;
            }
            buckets[i] -= nums[numsIndex];
        }
        return false;
    }

    public static void main(String[] args) {
        CanPartitionKSubsets subsets = new CanPartitionKSubsets();
        int[] nums = new int[]{1, 1, 1, 1, 2, 2, 2, 2};
        int k = 2;
        boolean flag = subsets.canPartitionKSubsets(nums, k);
        assert flag;
    }
}

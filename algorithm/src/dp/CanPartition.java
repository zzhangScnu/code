package dp;

// 416-分割等和子集
// partition-equal-subset-sum
//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。
//
// 示例 2：
//
//
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 200
// 1 <= nums[i] <= 100
//
// Related Topics 数组 动态规划

/**
 * @author lihua
 * @since 2021/12/24
 */
public class CanPartition {

    public boolean canPartition(int[] nums) {
        int sum = sum(nums);
        if (sum % 2 != 0) {
            return false;
        }
        int weight = sum / 2;
        int length = nums.length;
        boolean[][] dp = initial(length, weight);
        // 结束条件必须是=的，因为索引是从1开始的
        for (int i = 1; i <= length; i++) {
            for (int w = 1; w <= weight; w++) {
                if (w < nums[i - 1]) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 如果拿时刚好装满，那么对i - 1个物品，背包容量减去当前物品重量时，也应该刚好装满
                    // 如果不拿时刚好装满，则继承对i - 1个物品时的状态
                    dp[i][w] = dp[i - 1][w - nums[i - 1]] || dp[i - 1][w];
                }
            }
        }
        return dp[length][weight];
    }

    private boolean[][] initial(int length, int weight) {
        boolean[][] dp = new boolean[length + 1][weight + 1];
        // 对于背包容量为0的情况，其实算是装满了
        for (int i = 0; i <= length; i++) {
            dp[i][0] = true;
        }
        return dp;
    }

    private int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        CanPartition clazz = new CanPartition();
        int[] nums = new int[]{1, 2, 5};
        boolean flag = clazz.canPartition(nums);
        assert !flag;
        flag = clazz.canPartitionInCompress(nums);
        assert !flag;
    }

    /**
     * 状态压缩版
     * 因为每行的状态转移都只依赖于上一行的状态，所以只需要用一维数组存储结果即可
     * i每进行一轮迭代，操作的dp[w]就相当于上一轮的dp[i - 1][w]
     */
    public boolean canPartitionInCompress(int[] nums) {
        int sum = sum(nums);
        if (sum % 2 != 0) {
            return false;
        }
        int weight = sum / 2;
        int length = nums.length;
        boolean[] dp = initial(weight);
        // 结束条件必须是=的，因为索引是从1开始的
        for (int i = 1; i <= length; i++) {
            // 这里必须从后往前，才能对每一个物品做选择的时候不覆盖之前的dp值
            for (int w = weight; w >= 0; w--) {
                if (w >= nums[i - 1]) {
                    // 如果拿时刚好装满，那么对i - 1个物品，背包容量减去当前物品重量时，也应该刚好装满
                    // 如果不拿时刚好装满，则继承对i - 1个物品时的状态
                    dp[w] = dp[w - nums[i - 1]] || dp[w];
                }
            }
        }
        return dp[weight];
    }

    private boolean[] initial(int weight) {
        boolean[] dp = new boolean[weight + 1];
        // 对于背包容量为0的情况，其实算是装满了
        dp[0] = true;
        return dp;
    }
}

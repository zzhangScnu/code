package dp;

//给你一个可装载重量为W的背包和N个物品，每个物品有重量和价值两个属性。其中第i个物品的重量为wt[i]，价值为val[i]，现在让你用这个背包装物品，最多能装的价值是多少？
//
//举个简单的例子，输入如下：
//
//N = 3, W = 4
//wt = [2, 1, 3]
//val = [4, 2, 3]
//算法返回 6，选择前两件物品装进背包，总重量 3 小于W，可以获得最大价值 6。
//
//题目就是这么简单，一个典型的动态规划问题。这个题目中的物品不可以分割，要么装进包里，要么不装，不能说切成两块装一半。这也许就是 0-1 背包这个名词的来历。

/**
 * 步骤：
 * 1. 确定【状态】和【选择】
 * 2. 明确dp数组含义
 * 3. 遍历不同的状态，做出选择，填写dp数组
 * <p>
 * 举例：
 * 1. 状态：当前物品 + 背包容量 -> dp数组
 * 选择：拿 / 不拿
 * 2. dp数组的含义：
 * dp[i][w]表示对于前i件物品来说，如果背包容量为w时的最大价值
 * 3. 遍历 + 选择 + 填表
 *
 * @author lihua
 * @since 2021/12/21
 */
public class PackageSolution {

    public int solve(int[] weights, int[] values, int maxWeight) {
        int length = weights.length;
        int[][] dp = new int[length + 1][maxWeight + 1];
        // 从1开始避免数组越界
        for (int i = 1; i <= length; i++) {
            for (int w = 1; w <= maxWeight; w++) {
                // 如果当前容量不足以拿取当前物品，则当前价值等于前i - 1件物品时的价值
                if (w < weights[i - 1]) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 不拿和拿之间取价值大者
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            // 因为i从1开始，所以weights和values的取值是i - 1
                            dp[i - 1][w - weights[i - 1]] + values[i - 1]
                    );
                }
            }
        }
        return dp[length][maxWeight];
    }

    public static void main(String[] args) {
        PackageSolution clazz = new PackageSolution();
        int[] weights = new int[]{2, 1, 3};
        int[] values = new int[]{4, 2, 3};
        int maxWeight = 4;
        int result = clazz.solve(weights, values, maxWeight);
        assert result == 6;
    }
}

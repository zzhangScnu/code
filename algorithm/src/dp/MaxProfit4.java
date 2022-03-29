package dp;

// 188-买卖股票的最佳时机 IV
// best-time-to-buy-and-sell-stock-iv
//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
//
//
// 示例 1：
//
//
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
//
// 示例 2：
//
//
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3
//。
//
//
//
// 提示：
//
//
// 0 <= k <= 100
// 0 <= prices.length <= 1000
// 0 <= prices[i] <= 1000
//
// Related Topics 数组 动态规划

/**
 * @author lihua
 * @since 2022/3/25
 */
public class MaxProfit4 {

    /**
     * dp[i][k][j]
     * i：天数
     * k：买卖次数
     * j：1-持有；0-不持有
     * <p>
     * dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
     */
    public int maxProfit(int k, int[] prices) {
        int length = prices.length;
        if (length <= 0) {
            return 0;
        }
        // k + 1的原因是，k = 0的时候实际上是base case，是不允许交易的情况
        int[][][] dp = new int[length][k + 1][2];
        for (int i = 0; i < length; i++) {
            for (int m = 0; m <= k; m++) {
                if (m == 0) {
                    dp[i][m][0] = 0;
                    dp[i][m][1] = Integer.MIN_VALUE;
                    continue;
                }
                if (i == 0) {
                    dp[i][m][0] = 0;
                    // 把i=0代进右边式子，得出结果
                    dp[i][m][1] = -prices[i];
                    continue;
                }
                dp[i][m][0] = Math.max(dp[i - 1][m][0], dp[i - 1][m][1] + prices[i]);
                dp[i][m][1] = Math.max(dp[i - 1][m][1], dp[i - 1][m - 1][0] - prices[i]);
            }
        }
        return dp[length - 1][k][0];
    }

    public static void main(String[] args) {
        MaxProfit4 maxProfit4 = new MaxProfit4();
        int result = maxProfit4.maxProfit(2, new int[]{});
        System.out.println(result);
        assert result == 7;
    }
}

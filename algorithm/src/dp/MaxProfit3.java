package dp;

// 123-买卖股票的最佳时机 III
// best-time-to-buy-and-sell-stock-iii
//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
//
//
// 示例 1:
//
//
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
//
// 示例 2：
//
//
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
//
//
// 示例 3：
//
//
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
//
// 示例 4：
//
//
//输入：prices = [1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= prices.length <= 105
// 0 <= prices[i] <= 105
//
// Related Topics 数组 动态规划

/**
 * dp[i][k][j]
 * <p>
 * dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k][0] - prices[i])
 * dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k - 1][1] + prices[i])
 *
 * @author lihua
 * @since 2022/3/25
 */
public class MaxProfit3 {

    public int maxProfit(int[] prices) {
        int length = prices.length;
        int times = 2;
        int[][][] dp = initDp(length, times);
        // i = 0时的base case在initDp里面做了
        for (int i = 1; i < length; i++) {
            // 交易次数是由多变少的，所以这里是递减for
            for (int k = times; k >= 1; k--) {
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k][0] - prices[i]);
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k - 1][1] + prices[i]);
            }
        }
        return dp[length - 1][times - 1][0];
    }

    /**
     * todo: base case没做好
     */
    private int[][][] initDp(int length, int times) {
        int[][][] dp = new int[length][times + 1][2];
        for (int k = 1; k <= times; k++) {
            dp[0][k][0] = 0;
            dp[0][k][1] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }
        return dp;
    }

    /**
     * dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
     * dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
     * dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]); => dp[i][1][1] = Math.max(dp[i - 1][1][1], - prices[i]);
     * dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
     */
    public int maxProfitSimple(int[] prices) {
        // 当i = -1，即未开始
        int dp_i_1_0 = 0;
        int dp_i_1_1 = Integer.MIN_VALUE;
        int dp_i_2_0 = 0;
        int dp_i_2_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i_1_0 = Math.max(dp_i_1_0, dp_i_1_1 + price);
            dp_i_1_1 = Math.max(dp_i_1_1, -price);
            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_2_1 + price);
            dp_i_2_1 = Math.max(dp_i_2_1, dp_i_1_0 - price);
        }
        return dp_i_2_0;
    }

    public static void main(String[] args) {
        MaxProfit3 maxProfit3 = new MaxProfit3();
        int maxProfit = maxProfit3.maxProfitSimple(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
        System.out.println(maxProfit);
        assert maxProfit == 6;
    }
}

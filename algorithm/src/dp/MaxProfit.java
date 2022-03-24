package dp;

// 121-买卖股票的最佳时机
// best-time-to-buy-and-sell-stock
//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
//
//
//
// 示例 1：
//
//
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
//
//
// 示例 2：
//
//
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
//
//
//
//
// 提示：
//
//
// 1 <= prices.length <= 105
// 0 <= prices[i] <= 104
//
// Related Topics 数组 动态规划

/**
 * dp[i][k][j]
 * i：第i天，0 ~ length - 1
 * k：交易k次，1 ~ k
 * j：0-没有持有；1-持有
 * <p>
 * dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
 * dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
 *
 * @author lihua
 * @since 2022/3/23
 */
public class MaxProfit {

    /**
     * k = 1
     * 代入可得
     * dp[i][1][1] = max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i])
     * dp[i][1][0] = max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i])
     * <p>
     * dp[i - 1][0][0]：如果k=0，代表不可交易，此时j=0代表手上没有股票，所以该值为0
     * <p>
     * PS:k的减1操作可以放在买入，也可以放在卖出，但是因为上面这个base case，如果放在卖出的话，
     * dp[i - 1][0][1]：如果k=0，代表不可交易，此时j=1代表手上有股票，不合常理，所以该值为-infinite，这个在程序中不好表示
     * 所以k-1放在买入处理
     * <p>
     * 然后可以发现，整个式子跟k没有关系了，可以约简为二维dp
     * dp[i][1] = max(dp[i - 1][1], - prices[i])
     * dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
     * <p>
     * 可以进一步发现，每次dp的i变化时的值，只跟上一次i-1的变化有关
     * 所以可以约简为：
     * dp_i_1 = max(dp_i_1, -prices[i])
     * dp_i_0 = max(dp_i_0, dp_i_1 + prices[i])
     * <p>
     * 最后一天一定是卖出股票的利润最大，所以直接返回dp_i_0
     */
    public int maxProfit(int[] prices) {
        int length = prices.length;
        // 相当于初始化i = -1的情况
        int dp_i_1 = Integer.MIN_VALUE;
        int dp_i_0 = 0;
        for (int i = 0; i < length; i++) {
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
        int profit = maxProfit.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(profit);
        assert profit == 5;
    }
}

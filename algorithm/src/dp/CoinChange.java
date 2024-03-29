package dp;

// 322-零钱兑换
// coin-change
//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
//
// 你可以认为每种硬币的数量是无限的。
//
//
//
// 示例 1：
//
//
//输入：coins = [1, 2, 5], amount = 11
//输出：3
//解释：11 = 5 + 5 + 1
//
// 示例 2：
//
//
//输入：coins = [2], amount = 3
//输出：-1
//
// 示例 3：
//
//
//输入：coins = [1], amount = 0
//输出：0
//
//
// 示例 4：
//
//
//输入：coins = [1], amount = 1
//输出：1
//
//
// 示例 5：
//
//
//输入：coins = [1], amount = 2
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 231 - 1
// 0 <= amount <= 104
//
// Related Topics 广度优先搜索 数组 动态规划

/**
 * @author lihua
 * @since 2021/12/13
 */
public class CoinChange {

    /**
     * 求最少硬币个数
     * f(0) = 0
     * f(amount) = min{f(amount - coin) + 1, f(amount)}
     */
    public int coinChange(int[] coins, int amount) {
        int length = amount + 1;
        int[] minCoinsOfAmount = initialDpTable(amount);
        for (int thisAmount = 0; thisAmount < length; thisAmount++) {
            for (int coin : coins) {
                int nextAmount = thisAmount - coin;
                if (nextAmount < 0) {
                    continue;
                }
                minCoinsOfAmount[thisAmount] = Math.min(
                        minCoinsOfAmount[thisAmount], minCoinsOfAmount[nextAmount] + 1);
            }
        }
        return minCoinsOfAmount[amount] == Integer.MAX_VALUE - 1 ? -1 : minCoinsOfAmount[amount];
    }

    private int[] initialDpTable(int amount) {
        int length = amount + 1;
        int[] minCoinsOfAmount = new int[length];
        for (int i = 0; i < length; i++) {
            // 初始化为最大值-1，否则在循环里比较的时候会上溢为负数
            minCoinsOfAmount[i] = Integer.MAX_VALUE - 1;
        }
        minCoinsOfAmount[0] = 0;
        return minCoinsOfAmount;
    }

    public static void main(String[] args) {
        CoinChange clazz = new CoinChange();
        int result = clazz.coinChange(new int[]{5}, 11);
        assert result == 3;
    }
}

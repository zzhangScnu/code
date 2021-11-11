package slidingwindow;
// 1423-可获得的最大点数
// maximum-points-you-can-obtain-from-cards
//几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
//
// 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
//
// 你的点数就是你拿到手中的所有卡牌的点数之和。
//
// 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
//
//
//
// 示例 1：
//
// 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
//输出：12
//解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5
// = 12 。
//
//
// 示例 2：
//
// 输入：cardPoints = [2,2,2], k = 2
//输出：4
//解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
//
//
// 示例 3：
//
// 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
//输出：55
//解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
//
//
// 示例 4：
//
// 输入：cardPoints = [1,1000,1], k = 1
//输出：1
//解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
//
//
// 示例 5：
//
// 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
//输出：202
//
//
//
//
// 提示：
//
//
// 1 <= cardPoints.length <= 10^5
// 1 <= cardPoints[i] <= 10^4
// 1 <= k <= cardPoints.length
//
// Related Topics 数组 前缀和 滑动窗口

/**
 * @author lihua
 * @since 2021/11/10
 */
public class MaxScore {

    public int maxScore(int[] cardPoints, int k) {
        int left = 0, right = 0;
        int score = 0, minScore = Integer.MAX_VALUE;
        int length = cardPoints.length;
        int windowLength = length - k;
        // 如果滑动窗口的长度计算为0，表示没有选择，只能全选的情况
        if (windowLength == 0) {
            return sum(cardPoints);
        }
        // 其实就是要求滑动窗口是最小点数的情况
        while (right < length) {
            score += cardPoints[right];
            right++;
            if (right - left + 1 > windowLength) {
                if (score < minScore) {
                    minScore = score;
                }
                score -= cardPoints[left];
                left++;
            }
        }
        int sum = sum(cardPoints);
        return sum - minScore;
    }

    private int sum(int[] cardPoints) {
        int sum = 0;
        for (int cardPoint : cardPoints) {
            sum += cardPoint;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] cardPoints = new int[]{1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        MaxScore clazz = new MaxScore();
        int result = clazz.maxScore(cardPoints, k);
        assert result == 12;
    }
}

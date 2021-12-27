package dp;

// 72-编辑距离
// edit-distance
//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作：
//
//
// 插入一个字符
// 删除一个字符
// 替换一个字符
//
//
//
//
// 示例 1：
//
//
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
//
//
// 示例 2：
//
//
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
//
//
//
//
// 提示：
//
//
// 0 <= word1.length, word2.length <= 500
// word1 和 word2 由小写英文字母组成
//
// Related Topics 字符串 动态规划

/**
 * 状态：[i][j]表示从0..i和0..j之间的最小编辑距离
 * 选择： 插 / 删 / 换 / 不动
 *
 * @author lihua
 * @since 2021/12/27
 */
public class MinDistance {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = initializeDp(len1, len2);
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 相等的时候，结果按兵不动，索引一起往前挪
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // i变成j
                    dp[i][j] = min(
                            // word1插，这时候j被匹配了，j往前移动
                            dp[i][j - 1] + 1,
                            // word1删，这时候i往前移，继续匹配
                            dp[i - 1][j] + 1,
                            // word1换，这时候i和j所指的字符串就匹配了，一起往前移
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }
        return dp[len1][len2];
    }

    private int[][] initializeDp(int len1, int len2) {
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 如果word2已经遍历完了，需要将word1剩下未遍历的字符一一删去
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        // 如果word1已经遍历完了，需要将word2剩下未遍历的字符一一插入word1
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }
        return dp;
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) {
        MinDistance clazz = new MinDistance();
        int result = clazz.minDistance("horse", "ros");
        assert result == 3;
    }
}

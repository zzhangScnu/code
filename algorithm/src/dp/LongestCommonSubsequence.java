package dp;

// 1143-最长公共子序列
// longest-common-subsequence
//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//
//
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
//
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
//
//
//
// 示例 1：
//
//
//输入：text1 = "abcde", text2 = "ace"
//输出：3
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
//
//
// 示例 2：
//
//
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
//
//
// 示例 3：
//
//
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
//
//
//
//
// 提示：
//
//
// 1 <= text1.length, text2.length <= 1000
// text1 和 text2 仅由小写英文字符组成。
//
// Related Topics 字符串 动态规划

/**
 * 状态：text1(i)，text2(j)，[i][j]表示当前text1的0-i / text2的0-j的最长公共子序列长度
 * 选择：放 / 不放，也就是等于 / 不等于
 *
 * @author lihua
 * @since 2021/12/27
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int length1 = text1.length();
        int length2 = text2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 如果字符相等的话，说明这个字符之前都不在text1或text2中
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 如果字符不相等的话，至少有一个字符不在text1或text2中，取长度最大者
                    // 此时不需要考虑字符都不在text1或text2中的情况，因为这时候长度会是三者之中最小的
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[length1][length2];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence clazz = new LongestCommonSubsequence();
        int result = clazz.longestCommonSubsequence("bsbininm", "jmjkbkjkv");
        assert result == 1;
    }
}

package dp;

// 516-最长回文子序列
// longest-palindromic-subsequence
//给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
//
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
//
//
//
// 示例 1：
//
//
//输入：s = "bbbab"
//输出：4
//解释：一个可能的最长回文子序列为 "bbbb" 。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出：2
//解释：一个可能的最长回文子序列为 "bb" 。
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由小写英文字母组成
//
// Related Topics 字符串 动态规划

/**
 * 思路：
 * 先确定状态转移方程
 * 再根据填表需要的已知格子，确认遍历方向
 * <p>
 * 比如本例，counter代表i..j中最长的回文子序列
 * 而一个字符本身就是一个回文串，即counter[i][i] = 1，也就是↘对角线的值都是1
 * j恒大于等于i，即↘对角线往左都是0
 * 结果存在counter[0..n - 1]，即最右上的格子中
 * 由状态转移方程得知，counter[i][j]有可能从counter[i + 1][j - 1]、counter[i + 1][j]、counter[i][j - 1]得来
 * 则遍历的方向是，↗
 * 所以两个for循环中，第一个for是倒序的
 *
 * @author lihua
 * @since 2021/12/15
 */
public class LongestPalindromeSubseq {

    public int longestPalindromeSubseq(String s) {
        int length = s.length();
        if (length == 1) {
            return length;
        }
        int[][] counter = initialCounter(length);
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    counter[i][j] = counter[i + 1][j - 1] + 2;
                } else {
                    counter[i][j] = Math.max(counter[i + 1][j], counter[i][j - 1]);
                }
            }
        }
        return counter[0][length - 1];
    }

    private int[][] initialCounter(int length) {
        // 默认初始化为0
        int[][] counter = new int[length][length];
        for (int i = 0; i < length; i++) {
            counter[i][i] = 1;
        }
        return counter;
    }

    public static void main(String[] args) {
        LongestPalindromeSubseq clazz = new LongestPalindromeSubseq();
        int length = clazz.longestPalindromeSubseq("abba");
        assert length == 4;
    }
}

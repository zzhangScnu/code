package dp;

// 5-最长回文子串
// longest-palindromic-substring
//给你一个字符串 s，找到 s 中最长的回文子串。
//
//
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
// 示例 3：
//
//
//输入：s = "a"
//输出："a"
//
//
// 示例 4：
//
//
//输入：s = "ac"
//输出："a"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母（大写和/或小写）组成
//
// Related Topics 字符串 动态规划

/**
 * @author lihua
 * @since 2021/12/14
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int length = s.length();
        if (length == 1) {
            return s;
        }
        int maxLength = 1;
        int maxStart = 0;
        boolean[][] palindrome = initial(length);
        int end;
        // 长度循环一定要在外层，因为要从短的回文串渐进(转移)到长的，palindrome里面的数据才会准确
        for (int subLength = 2; subLength <= length; subLength++) {
            for (int begin = 0; begin < length; begin++) {
                end = begin + subLength - 1;
                if (end >= length) {
                    continue;
                }
                if (s.charAt(begin) != s.charAt(end)) {
                    palindrome[begin][end] = false;
                } else {
                    if (subLength == 2) {
                        palindrome[begin][end] = true;
                    } else {
                        palindrome[begin][end] = palindrome[begin + 1][end - 1];
                    }
                }
                if (palindrome[begin][end] && subLength > maxLength) {
                    maxLength = subLength;
                    maxStart = begin;
                }
            }
        }
        return s.substring(maxStart, maxStart + maxLength);
    }

    private boolean[][] initial(int length) {
        boolean[][] palindrome = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            // 长度为1的字符串就是一个回文串
            palindrome[i][i] = true;
        }
        return palindrome;
    }

    public static void main(String[] args) {
        LongestPalindrome clazz = new LongestPalindrome();
        String longestPalindrome = clazz.longestPalindrome("abba");
        assert longestPalindrome.length() == 4;
    }
}

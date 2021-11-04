package array;
// 541-反转字符串 II
// reverse-string-ii
//给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
//
//
// 如果剩余字符少于 k 个，则将剩余字符全部反转。
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
//
//
//
//
// 示例 1：
//
//
//输入：s = "abcdefg", k = 2
//输出："bacdfeg"
//
//
// 示例 2：
//
//
//输入：s = "abcd", k = 2
//输出："bacd"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 仅由小写英文组成
// 1 <= k <= 104
//
// Related Topics 双指针 字符串

/**
 * @author lihua
 * @since 2021/11/4
 */
public class ReverseStr {

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int length = s.length();
        for (int i = 0; i < length; i += 2 * k) {
            // right的取值，满足了题意
            revert(chars, i, Math.min(i + k, length) - 1);
        }
        return new String(chars);
    }

    private void revert(char[] chars, int left, int right) {
        char temp;
        while (left < right) {
            temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        ReverseStr clazz = new ReverseStr();
        String s = "abcdefg";
        int k = 8;
        String result = clazz.reverseStr(s, k);
        assert result.equals("gfedcba");
    }
}

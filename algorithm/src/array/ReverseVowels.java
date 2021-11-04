package array;
// 345-反转字符串中的元音字母
// reverse-vowels-of-a-string
//给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
//
// 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
//
//
//
// 示例 1：
//
//
//输入：s = "hello"
//输出："holle"
//
//
// 示例 2：
//
//
//输入：s = "leetcode"
//输出："leotcede"
//
//
//
// 提示：
//
//
// 1 <= s.length <= 3 * 105
// s 由 可打印的 ASCII 字符组成
//
// Related Topics 双指针 字符串

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lihua
 * @since 2021/11/4
 */
public class ReverseVowels {

    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        char temp;
        while (left < right) {
            while (!vowels.contains(chars[left]) && left < right) {
                left++;
            }
            while (!vowels.contains(chars[right]) && left < right) {
                right--;
            }
            temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        ReverseVowels clazz = new ReverseVowels();
        String s = "leetcode";
        String result = clazz.reverseVowels(s);
        assert result.equals("leotcede");
    }
}

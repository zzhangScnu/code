package slidingwindow;
// 3-无重复字符的最长子串
// longest-substring-without-repeating-characters
//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
// 示例 4:
//
//
//输入: s = ""
//输出: 0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 104
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 字符串 滑动窗口

import java.util.HashMap;
import java.util.Map;

/**
 * @author lihua
 * @since 2021/11/6
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0;
        Map<Character, Integer> window = new HashMap<>();
        int length = s.length();
        int maxLength = 0;
        while (r < length) {
            char rCur = s.charAt(r);
            window.put(rCur, window.getOrDefault(rCur, 0) + 1);
            r++;
            // 如果此时滑动窗口中有重复值了，就不断移动左指针，直到不再有重复
            // 窗口长度可变时，如果窗口不合法的话，需要不断收缩上界，直到再次合法
            while (window.get(rCur) > 1) {
                char lCur = s.charAt(l);
                window.put(lCur, window.getOrDefault(lCur, 0) - 1);
                l++;
            }
            // 不再重复后，试图更新结果
            maxLength = Math.max(maxLength, r - l);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring clazz = new LengthOfLongestSubstring();
        // 子串：连续字符串 子序列：不一定要连续
        int length = clazz.lengthOfLongestSubstring("pwwkew");
        assert length == 3;
    }
}

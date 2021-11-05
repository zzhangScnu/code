package slidingwindow;
// 567-字符串的排列
// permutation-in-string
//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
//
// 换句话说，s1 的排列之一是 s2 的 子串 。
//
//
//
// 示例 1：
//
//
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
//
//
// 示例 2：
//
//
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= s1.length, s2.length <= 104
// s1 和 s2 仅包含小写字母
//
// Related Topics 哈希表 双指针 字符串 滑动窗口

import java.util.HashMap;
import java.util.Map;

/**
 * @author lihua
 * @since 2021/11/5
 */
@SuppressWarnings("DuplicatedCode")
public class CheckInclusion {

    /**
     * 只要找到任何一种符合条件的情况，就返回true
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = initialNeed(s1);
        Map<Character, Integer> window = new HashMap<>();
        char[] chars = s2.toCharArray();
        int length = chars.length;
        int left = 0;
        int right = 0;
        int valid = 0;
        int start = 0;
        int minLength = Integer.MAX_VALUE;
        char cur;
        while (right < length) {
            cur = chars[right];
            if (need.containsKey(cur)) {
                window.put(cur, window.getOrDefault(cur, 0) + 1);
                if (window.get(cur).equals(need.get(cur))) {
                    valid++;
                }
            }
            right++;
            while (valid == need.size()) {
                if (minLength > right - left) {
                    minLength = right - left;
                    start = left;
                }
                cur = chars[left];
                if (need.containsKey(cur)) {
                    if (window.get(cur).equals(need.get(cur))) {
                        valid--;
                    }
                    window.put(cur, window.get(cur) - 1);
                }
                left++;
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return false;
        }
        String res = s2.substring(start, start + minLength);
        return res.length() == s1.length();
    }

    private Map<Character, Integer> initialNeed(String s1) {
        Map<Character, Integer> need = new HashMap<>();
        char[] chars = s1.toCharArray();
        for (char ch : chars) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        return need;
    }

    public static void main(String[] args) {
        CheckInclusion checkInclusion = new CheckInclusion();
        boolean flag = checkInclusion.checkInclusion("ab", "eidboaoo");
        assert flag;
    }
}

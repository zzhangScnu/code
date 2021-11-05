package slidingwindow;
// 76-最小覆盖子串
// minimum-window-substring
//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
//
//
// 注意：
//
//
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。
//
//
//
//
// 示例 1：
//
//
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//
//
// 示例 2：
//
//
//输入：s = "a", t = "a"
//输出："a"
//
//
// 示例 3:
//
//
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。
//
//
//
// 提示：
//
//
// 1 <= s.length, t.length <= 105
// s 和 t 由英文字母组成
//
//
//
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口

import java.util.HashMap;
import java.util.Map;

/**
 * @author lihua
 * @since 2021/11/5
 */
public class MinWindow {

    public String minWindow(String s, String t) {
        // t中出现的字母 -> 次数
        Map<Character, Integer> need = initialNeed(t);
        // 当前滑动窗口中，满足t中出现的字母 -> 次数
        Map<Character, Integer> window = new HashMap<>();
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        // window中已有多少个字母满足出现次数
        int valid = 0;
        // 子串的开始索引
        int start = 0;
        // 子串的最小长度
        int minLength = Integer.MAX_VALUE;
        char current;
        // 右指针一直向右，直到末尾
        while (right < chars.length) {
            current = chars[right];
            if (need.containsKey(current)) {
                window.put(current, window.getOrDefault(current, 0) + 1);
                if (window.get(current).equals(need.get(current))) {
                    valid++;
                }
            }
            right++;
            // 当滑动窗口中已经满足need的要求时，开始缩减左区间，向右推动左指针
            while (valid == need.size()) {
                // 当仍满足题意时，不断更新子串属性
                if (right - left < minLength) {
                    start = left;
                    minLength = right - left;
                }
                current = chars[left];
                if (need.containsKey(current)) {
                    // 这两步操作，跟右指针右移的时候刚好是相反的
                    if (window.get(current).equals(need.get(current))) {
                        valid--;
                    }
                    window.put(current, window.get(current) - 1);
                }
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }

    private Map<Character, Integer> initialNeed(String t) {
        char[] chars = t.toCharArray();
        Map<Character, Integer> need = new HashMap<>();
        for (char ch : chars) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        return need;
    }

    public static void main(String[] args) {
        MinWindow minWindow = new MinWindow();
        String result = minWindow.minWindow("ADOBECODEBANC", "ABC");
        assert "BANC".equals(result);
    }
}

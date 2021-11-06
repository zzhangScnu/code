package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// 438-找到字符串中所有字母异位词
// find-all-anagrams-in-a-string
//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
//
//
//
// 示例 1:
//
//
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
//
//
// 示例 2:
//
//
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
//
//
//
//
// 提示:
//
//
// 1 <= s.length, p.length <= 3 * 104
// s 和 p 仅包含小写字母
//
// Related Topics 哈希表 字符串 滑动窗口

/**
 * @author lihua
 * @since 2021/11/6
 */
public class FindAnagrams {

    private List<Integer> resultList = new ArrayList<>();

    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = initialMap(p);
        Map<Character, Integer> window = new HashMap<>();
        int l = 0, r = 0, valid = 0;
        char cur;
        int length = s.length();
        while (r < length) {
            cur = s.charAt(r);
            if (need.containsKey(cur)) {
                window.put(cur, window.getOrDefault(cur, 0) + 1);
                if (need.get(cur).equals(window.get(cur))) {
                    valid++;
                }
            }
            r++;
            while (valid == need.size()) {
                cur = s.charAt(l);
                // 是p的长度而不能是need的大小，否则处理不了重复字符串的情况
                if (r - l == p.length()) {
                    resultList.add(l);
                }
                if (need.containsKey(cur)) {
                    if (need.get(cur).equals(window.get(cur))) {
                        valid--;
                    }
                    window.put(cur, window.get(cur) - 1);
                }
                l++;
            }
        }
        return resultList;
    }

    private Map<Character, Integer> initialMap(String p) {
        Map<Character, Integer> need = new HashMap<>();
        char[] chars = p.toCharArray();
        for (char ch : chars) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        return need;
    }

    public static void main(String[] args) {
        FindAnagrams clazz = new FindAnagrams();
        List<Integer> resultList = clazz.findAnagrams("baa", "aa");
        assert resultList.size() == 1;
    }
}

package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// 30-串联所有单词的子串
// substring-with-concatenation-of-all-words
//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
//
//
//
// 示例 1：
//
//
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
//
//
// 示例 2：
//
//
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
//
//
// 示例 3：
//
//
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 由小写英文字母组成
// 1 <= words.length <= 5000
// 1 <= words[i].length <= 30
// words[i] 由小写英文字母组成
//
// Related Topics 哈希表 字符串 滑动窗口

/**
 * s中不是只由words中的单词的长度的词组成的，中间可以存在单个或多个字符
 *
 * @author lihua
 * @since 2021/11/8
 */
public class FindSubstring {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> resultList = new ArrayList<>();
        int lengthPerWord = words[0].length();
        Map<String, Integer> needs = initialNeed(words);
        Map<String, Integer> window = new HashMap<>();
        int left = 0, valid = 0;
        int totalLength = s.length();
        int needSize = needs.size();
        int requiredLength = (words.length - 1) * lengthPerWord;
        String currentWord;
        for (int right = 0; right < totalLength; right++) {
            currentWord = s.substring(right, Math.min(right + lengthPerWord, totalLength));
            if (needs.containsKey(currentWord)) {
                window.put(currentWord, window.getOrDefault(currentWord, 0) + 1);
                if (window.get(currentWord).equals(needs.get(currentWord))) {
                    valid++;
                }
            }
            while (valid == needSize) {
                // todo: 不知道这里用什么判断好
                if (right - left == requiredLength) {
                    resultList.add(left);
                }
                currentWord = s.substring(left, left + lengthPerWord);
                if (needs.containsKey(currentWord)) {
                    if (window.get(currentWord).equals(needs.get(currentWord))) {
                        valid--;
                    }
                    window.put(currentWord, window.getOrDefault(currentWord, 0) - 1);
                }
                left++;
            }
        }
        return resultList;
    }

    private Map<String, Integer> initialNeed(String[] words) {
        Map<String, Integer> need = new HashMap<>();
        for (String word : words) {
            need.put(word, need.getOrDefault(word, 0) + 1);
        }
        return need;
    }

    public static void main(String[] args) {
        FindSubstring clazz = new FindSubstring();
        // 0, 9
        assert clazz.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}).size() == 2;
        // 13
        assert clazz.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo", "barr", "wing", "ding", "wing"}).size() == 1;
        assert clazz.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}).isEmpty();
        // 6,9,12
        assert clazz.findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}).size() == 3;
        // 0
        assert clazz.findSubstring("ababababab", new String[]{"ababa", "babab"}).size() == 1;
    }
}

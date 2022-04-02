package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 49-字母异位词分组
// group-anagrams
//给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
//
// 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
//
//
//
// 示例 1:
//
//
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
//
// 示例 2:
//
//
//输入: strs = [""]
//输出: [[""]]
//
//
// 示例 3:
//
//
//输入: strs = ["a"]
//输出: [["a"]]
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 104
// 0 <= strs[i].length <= 100
// strs[i] 仅包含小写字母
//
// Related Topics 哈希表 字符串 排序

/**
 * OD
 * <p>
 * 对每个字符串排序，如果互为异位词，则排序后应该相等
 *
 * @author lihua
 * @since 2022/4/1
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strArr = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        List<List<String>> resultList = groupAnagrams.groupAnagrams(strArr);
        System.out.println(resultList);
    }

    public List<List<String>> groupAnagrams(String[] strArr) {
        Map<String, List<String>> counter = new HashMap<>(16);
        char[] strChars;
        List<String> groupList;
        String orderedStr;
        for (String str : strArr) {
            strChars = str.toCharArray();
            Arrays.sort(strChars);
            orderedStr = new String(strChars);
            groupList = counter.getOrDefault(orderedStr, new ArrayList<>());
            groupList.add(str);
            counter.put(orderedStr, groupList);
        }
        return new ArrayList<>(counter.values());
    }
}

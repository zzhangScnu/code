package base;

import java.util.ArrayList;
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
 *
 * @author lihua
 * @since 2022/4/1
 */
public class GroupAnagrams {

    /**
     * todo：fix this
     */
    public static void main(String[] args) {
        int[] nums = new int[]{123, 321, 213, 333, 444};
        GroupAnagrams numbersDivide = new GroupAnagrams();
        List<List<Integer>> resultList = numbersDivide.divide(nums);
        System.out.println(resultList);
    }

    public List<List<Integer>> divide(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>(16);
        int length = nums.length;
        int num;
        for (int i = 0; i < length; i++) {
            List<Integer> result = new ArrayList<>(16);
            num = nums[i];
            result.add(num);
            Map<Integer, Integer> counter = count(num);
            for (int j = i + 1; j < length; j++) {
                if (judgeAndRecord(nums[j], counter, result)) {
                    i = j;
                }
            }
            resultList.add(result);
        }
        return resultList;
    }

    private boolean judgeAndRecord(int candidate, Map<Integer, Integer> counter, List<Integer> result) {
        Map<Integer, Integer> candidateCounter = count(candidate);
        if (candidateCounter.equals(counter)) {
            result.add(candidate);
            return true;
        }
        return false;
    }

    private Map<Integer, Integer> count(int num) {
        Map<Integer, Integer> counter = new HashMap<>(16);
        int bit;
        while (num != 0) {
            bit = num % 10;
            counter.put(bit, counter.getOrDefault(bit, 0) + 1);
            num /= 10;
        }
        return counter;
    }
}

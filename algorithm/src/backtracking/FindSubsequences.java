package backtracking;
//给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
//
// 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
//
//
//
// 示例 1：
//
//
//输入：nums = [4,6,7,7]
//输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
//
//
// 示例 2：
//
//
//输入：nums = [4,4,3,2,1]
//输出：[[4,4]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 15
// -100 <= nums[i] <= 100
//
// Related Topics 位运算 数组 哈希表 回溯

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author lihua
 * @since 2021/11/2
 */
public class FindSubsequences {

    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrack(new LinkedList<>(), nums, 0);
        return resultList;
    }

    /**
     * 有点像子集II，但是不一样的地方有：
     * 1. 要判断track里面的元素有2个及以上才加入结果集
     * 2. 不能先排序再做去重，一排序就全都是递增子序列了
     * 所以要用一个标记set，标记本层已经用过的元素，避免重复
     */
    private void backtrack(LinkedList<Integer> track, int[] nums, int start) {
        if (track.size() >= 2) {
            resultList.add(new ArrayList<>(track));
        }
        int length = nums.length;
        Set<Integer> used = new HashSet<>();
        for (int i = start; i < length; i++) {
            // 比track的最后一个元素还要小的话，不符合条件
            if (!track.isEmpty() && track.getLast() > nums[i]) {
                continue;
            }
            // 记录本层已经用过的元素。用过的在这一层就不能再用了
            if (used.contains(nums[i])) {
                continue;
            }
            used.add(nums[i]);
            track.add(nums[i]);
            backtrack(track, nums, i + 1);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        FindSubsequences clazz = new FindSubsequences();
        int[] nums = new int[]{4, 4, 3, 2, 1};
        List<List<Integer>> resultList = clazz.findSubsequences(nums);
        assert resultList.size() == 1;
    }
}

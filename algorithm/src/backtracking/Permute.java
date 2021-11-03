package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
// 示例 2：
//
//
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// nums 中的所有整数 互不相同
//
// Related Topics 数组 回溯

/**
 * @author lihua
 * @since 2021/10/26
 */
public class Permute {

    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        // 用一个数组，通过选择和撤销，就可以记录所有可能性
        backtrack(nums, track);
        return resultList;
    }

    /**
     * 结束条件：走完了搜索树的一条分支，即track已经添加了nums中的所有元素
     * 选择列表：即将遍历的、仍未选择的元素
     * 路径：已遍历的、选择过的元素
     */
    private void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            resultList.add(new LinkedList<>(track));
            // 往回走，遍历其他可能性
            return;
        }
        // 在一棵树的某一层遍历
        for (int i = 0; i < nums.length; i++) {
            // 回溯前：选择
            if (track.contains(nums[i])) {
                // 由于不能有重复元素，已加入的就不能再选择了
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track);
            // 回溯后：撤销选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        Permute permute = new Permute();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> resultList = permute.permute(nums);
        assert resultList.size() == 6;
    }

    /**
     * 用标记数组减少判断元素是否已经选择过的时间复杂度
     */
    public static class PermuteByMark {

        private List<List<Integer>> resultList = new LinkedList<>();

        public List<List<Integer>> permute(int[] nums) {
            LinkedList<Integer> track = new LinkedList<>();
            int[] mark = new int[nums.length];
            backtrack(nums, mark, track);
            return resultList;
        }

        private void backtrack(int[] nums, int[] mark, LinkedList<Integer> track) {
            if (nums.length == track.size()) {
                resultList.add(new LinkedList<>(track));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (mark[i] == 1) {
                    continue;
                }
                track.add(nums[i]);
                mark[i] = 1;
                backtrack(nums, mark, track);
                track.removeLast();
                mark[i] = 0;
            }
        }
    }
}

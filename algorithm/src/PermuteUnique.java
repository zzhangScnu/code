//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 8
// -10 <= nums[i] <= 10
//
// Related Topics 数组 回溯

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author lihua
 * @since 2021/10/26
 */
@SuppressWarnings("DuplicatedCode")
public class PermuteUnique {

    private Set<List<Integer>> resultSet = new HashSet<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        int[] mark = new int[nums.length];
        backtrack(nums, mark, track);
        return new LinkedList<>(resultSet);
    }

    /**
     * 用标记数组判断元素是否已经加入了结果路径了
     * 由于有重复元素，就不能直接简单粗暴用contains了
     */
    private void backtrack(int[] nums, int[] mark, LinkedList<Integer> track) {
        if (nums.length == track.size()) {
            resultSet.add(new LinkedList<>(track));
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

    public static void main(String[] args) {
        PermuteUnique permute = new PermuteUnique();
        int[] nums = new int[]{1, 1, 2};
        List<List<Integer>> resultList = permute.permuteUnique(nums);
        assert resultList.size() == 3;
    }
}

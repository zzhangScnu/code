import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
//
//
// 示例 2：
//
//
//输入：nums = [0]
//输出：[[],[0]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
//
//
//
// Related Topics 位运算 数组 回溯

/**
 * @author lihua
 * @since 2021/11/1
 */
public class SubsetsII {

    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, 0, track);
        return resultList;
    }

    private void backtrack(int[] nums, int index, LinkedList<Integer> track) {
        resultList.add(new LinkedList<>(track));
        for (int i = index; i < nums.length; i++) {
            // 在树的一个节点上选择向下的下一个节点的时候，如果这个节点和之前遍历过的子树的根节点相同，就直接跳过
            // index是子树的第一个节点的索引
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            // 选择一个节点，走某条路径
            track.add(nums[i]);
            // 以这个节点为根节点，再继续往下深度遍历
            backtrack(nums, i + 1, track);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        SubsetsII subsets = new SubsetsII();
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> resultList = subsets.subsetsWithDup(nums);
        assert resultList.size() == 6;
    }
}

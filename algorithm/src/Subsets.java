import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
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
// nums 中的所有元素 互不相同
//
// Related Topics 位运算 数组 回溯

/**
 * @author lihua
 * @since 2021/10/27
 */
public class Subsets {

    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, 0, track);
        return resultList;
    }

    private void backtrack(int[] nums, int index, LinkedList<Integer> track) {
        resultList.add(new LinkedList<>(track));
        for (int i = index; i < nums.length; i++) {
            track.add(nums[i]);
            // i + 1，不是index + 1，要取后面递增的数字，入参的index是动态变化的。注意细节！
            backtrack(nums, i + 1, track);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> resultList = subsets.subsets(nums);
        assert resultList.size() == 8;
    }
}

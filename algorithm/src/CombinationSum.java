//给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的
//唯一组合。
//
// candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
//
// 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
//
//
//
// 示例 1：
//
//
//输入: candidates = [2,3,6,7], target = 7
//输出: [[7],[2,2,3]]
//
//
// 示例 2：
//
//
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]]
//
// 示例 3：
//
//
//输入: candidates = [2], target = 1
//输出: []
//
//
// 示例 4：
//
//
//输入: candidates = [1], target = 1
//输出: [[1]]
//
//
// 示例 5：
//
//
//输入: candidates = [1], target = 2
//输出: [[1,1]]
//
//
//
//
// 提示：
//
//
// 1 <= candidates.length <= 30
// 1 <= candidates[i] <= 200
// candidate 中的每个元素都是独一无二的。
// 1 <= target <= 500
//
// Related Topics 数组 回溯

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lihua
 * @since 2021/10/28
 */
public class CombinationSum {

    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 保证元素递增，可重复使用的元素也是相邻排列
        Arrays.sort(candidates);
        if (candidates[0] > target) {
            return resultList;
        }
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(candidates, target, 0, track);
        return resultList;
    }

    /**
     * target是减去当前选择元素的，这样就不用多一个数据结构保存当前各桶状况
     */
    private void backtrack(int[] candidates, int target, int beginIndex, LinkedList<Integer> track) {
        // 遍历完所有可选择的数了
        if (target == 0) {
            resultList.add(new ArrayList<>(track));
            return;
        }
        // 元素可以重复使用，所以每次循环都是从上次用过的位置开始
        for (int i = beginIndex; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                // 该位置的元素都已经不符合条件了，后面递增的元素也就不用判断了
                return;
            }
            track.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, track);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> resultList = combinationSum.combinationSum(candidates, target);
        // [[7],[2,2,3]]
        assert resultList.size() == 2;
    }
}

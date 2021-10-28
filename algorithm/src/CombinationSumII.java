//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的每个数字在每个组合中只能使用一次。
//
// 注意：解集不能包含重复的组合。
//
//
//
// 示例 1:
//
//
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//]
//
// 示例 2:
//
//
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//]
//
//
//
// 提示:
//
//
// 1 <= candidates.length <= 100
// 1 <= candidates[i] <= 50
// 1 <= target <= 30
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
public class CombinationSumII {

    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 保证元素递增，相同元素也是相邻排列
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
        // 已经获得想要的子集了
        if (target == 0) {
            resultList.add(new ArrayList<>(track));
            return;
        }
        for (int i = beginIndex; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                // 该位置的元素都已经不符合条件了，后面递增的元素也就不用判断了
                // break
                return;
            }
            // 等价的两种写法
            // while (i != beginIndex && candidates[i] == candidates[i - 1]) {
            //    i++;
            //}
            if (i != beginIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            track.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, track);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSumII combinationSum = new CombinationSumII();
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> resultList = combinationSum.combinationSum2(candidates, target);
        assert resultList.size() == 4;
    }
}

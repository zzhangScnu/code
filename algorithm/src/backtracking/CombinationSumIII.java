package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
//
// 说明：
//
//
// 所有数字都是正整数。
// 解集不能包含重复的组合。
//
//
// 示例 1:
//
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
//
//
// 示例 2:
//
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
//
// Related Topics 数组 回溯

/**
 * @author lihua
 * @since 2021/11/1
 */
public class CombinationSumIII {

    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(track, k, n, 1);
        return resultList;
    }

    /**
     * 求和可以考虑将当前目标值作为参数
     */
    private void backtrack(LinkedList<Integer> track, int k, int target, int num) {
        if (track.size() > k) {
            return;
        }
        if (target == 0 && track.size() == k) {
            resultList.add(new ArrayList<>(track));
            return;
        }
        // k - track.size() <= 9 - num + 1，才有可能最终凑到k个数字
        for (int i = num; i <= 9 - k + track.size() + 1; i++) {
            // 因为组合里不能有重复，即下一层的节点跟这一层已经选择的节点不能相同
            if (!track.isEmpty() && i <= track.getLast()) {
                continue;
            }
            track.add(i);
            backtrack(track, k, target - i, num + 1);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSumIII combinationSum = new CombinationSumIII();
        int k = 3;
        int n = 9;
        List<List<Integer>> resultList = combinationSum.combinationSum3(k, n);
        assert resultList.size() == 3;
    }
}

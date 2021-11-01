import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
//
// 你可以按 任何顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
//
// 示例 2：
//
//
//输入：n = 1, k = 1
//输出：[[1]]
//
//
//
// 提示：
//
//
// 1 <= n <= 20
// 1 <= k <= n
//
// Related Topics 数组 回溯

/**
 * @author lihua
 * @since 2021/10/29
 */
public class Combine {

    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(track, n, k);
        return resultList;
    }

    private void backtrack(LinkedList<Integer> track, int n, int k) {
        if (track.size() == k) {
            resultList.add(new ArrayList<>(track));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (track.isEmpty() || track.getLast() < i) {
                track.add(i);
                backtrack(track, n, k);
                track.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        Combine combine = new Combine();
        List<List<Integer>> resultList = combine.combine(4, 2);
        assert resultList.size() == 6;
    }
}

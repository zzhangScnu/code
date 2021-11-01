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
        if (n < k) {
            return resultList;
        }
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(track, 1, n, k);
        return resultList;
    }

    private void backtrack(LinkedList<Integer> track, int num, int n, int k) {
        // 还需n个元素 > 候选数组中后面还剩下的元素，此时元组里怎样都凑不齐k个元素了
        // 其实这里放到循环体里面更好，少了入栈出栈过程
        // if (k - track.size() > n - num + 1) {
        //     return;
        // }
        if (track.size() == k) {
            resultList.add(new ArrayList<>(track));
            return;
        }
        // 表示在i到上界区间中选取一个合适的数字（上界从n变成了剪枝条件的移项）
        for (int i = num; i <= n - k + track.size() + 1; i++) {
            track.add(i);
            // System.out.println("递归前： " + track);
            // num是i+1，不是num+1，多注意细节问题……
            backtrack(track, i + 1, n, k);
            track.removeLast();
            // System.out.println("递归后： " + track);
        }
    }

    public static void main(String[] args) {
        Combine combine = new Combine();
        List<List<Integer>> resultList = combine.combine(4, 2);
        assert resultList.size() == 6;
    }
}

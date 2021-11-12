package array;
// 56-合并区间
// merge-intervals
//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
//
//
//
// 示例 1：
//
//
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
//
// 示例 2：
//
//
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
//
//
//
// 提示：
//
//
// 1 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= starti <= endi <= 104
//
// Related Topics 数组 排序

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lihua
 * @since 2021/11/12
 */
public class Merge {

    public int[][] merge(int[][] intervals) {
        sort(intervals);
        int left = intervals[0][0];
        int right = intervals[0][1];
        int length = intervals.length;
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i <= length; i++) {
            // 如果已经遍历完了，就把最后一段也加到结果集里
            if (i == length) {
                res.add(new int[]{left, right});
                break;
            }
            if (intervals[i][0] >= left && intervals[i][1] <= right) {
                // 覆盖，啥也不干
            } else if (intervals[i][0] <= right && intervals[i][1] > right) {
                // 相交
                right = intervals[i][1];
            } else if (intervals[i][0] > right) {
                // 不相交。这时候记录结果，然后用下一个区间继续去合并之后的
                res.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        int[][] resArr = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }

    private void sort(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
    }

    public static void main(String[] args) {
        Merge clazz = new Merge();
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = clazz.merge(intervals);
        assert result.length == 3;
    }
}

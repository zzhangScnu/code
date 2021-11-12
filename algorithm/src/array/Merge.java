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

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @author lihua
 * @since 2021/11/12
 */
public class Merge {

    public int[][] merge(int[][] intervals) {
        sort(intervals);
        int length = intervals.length;
        LinkedList<int[]> res = new LinkedList<>();
        res.add(intervals[0]);
        int[] last;
        int[] cur;
        for (int i = 1; i < length; i++) {
            last = res.getLast();
            cur = intervals[i];
            // 两个区间有相交/覆盖，这时候取end的最大值
            if (cur[0] <= last[1]) {
                last[1] = Math.max(last[1], cur[1]);
            } else {
                // 否则不相交，将当前区间记录到结果集，并作为下一轮的比较基准
                res.add(new int[]{cur[0], cur[1]});
            }
        }
        int resSize = res.size();
        int[][] resArr = new int[resSize][];
        for (int i = 0; i < resSize; i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }

    /**
     * 按区间start升序排列
     */
    private void sort(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    }

    public static void main(String[] args) {
        Merge clazz = new Merge();
        int[][] intervals = new int[][]{{1, 4}, {2, 3}};
        int[][] result = clazz.merge(intervals);
        // 1, 4
        assert result.length == 1;
    }
}

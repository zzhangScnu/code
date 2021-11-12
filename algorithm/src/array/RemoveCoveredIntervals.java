package array;
// 1288-删除被覆盖区间
// remove-covered-intervals
//给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
//
// 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
//
// 在完成所有删除操作后，请你返回列表中剩余区间的数目。
//
//
//
// 示例：
//
//
//输入：intervals = [[1,4],[3,6],[2,8]]
//输出：2
//解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
//
//
//
//
// 提示：
//
//
// 1 <= intervals.length <= 1000
// 0 <= intervals[i][0] < intervals[i][1] <= 10^5
// 对于所有的 i != j：intervals[i] != intervals[j]
//
// Related Topics 数组 排序

import java.util.Arrays;

/**
 * @author lihua
 * @since 2021/11/12
 */
public class RemoveCoveredIntervals {

    /**
     * 题目是求删除被覆盖区间之后的区间数量
     * 其实就是求不被覆盖的区间的数量
     */
    public int removeCoveredIntervals(int[][] intervals) {
        sort(intervals);
        // 从第一个区间开始统计
        int left = intervals[0][0];
        int right = intervals[0][1];
        int res = 1;
        for (int[] interval : intervals) {
            if (interval[0] >= left && interval[1] <= right) {
                // 被包含
                // do nothing
            } else if (interval[0] <= right && interval[1] > right) {
                // 相交
                // 将右界扩展
                res++;
                right = interval[1];
            } else if (interval[0] > right) {
                // 不相交
                // 将左界右界都赋值为新的区间的，准备下一轮的统计
                res++;
                left = interval[0];
                right = interval[1];
            }
        }
        return res;
    }

    /**
     * 排一下序，假设区间首尾索引分别是l，r
     * 1. 按l升序排列
     * 2. l相同的情况下，按r降序排列
     * 目的是想让大的区间向下包住小的区间，这样后面比较好判断
     */
    private void sort(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> {
            if (i1[0] == i2[0]) {
                return i2[1] - i1[1];
            }
            return i1[0] - i2[0];
        });
    }

    public static void main(String[] args) {
        RemoveCoveredIntervals clazz = new RemoveCoveredIntervals();
        int[][] intervals = new int[][]{{1, 2}, {1, 4}, {3, 4}};
        int result = clazz.removeCoveredIntervals(intervals);
        assert result == 1;
    }
}

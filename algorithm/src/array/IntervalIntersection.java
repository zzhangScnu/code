package array;
// 986-区间列表的交集
// interval-list-intersections
//给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而 s
//econdList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。
//
// 返回这 两个区间列表的交集 。
//
// 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。
//
// 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。
//
//
//
// 示例 1：
//
//
//输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,
//24],[25,26]]
//输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
//
//
// 示例 2：
//
//
//输入：firstList = [[1,3],[5,9]], secondList = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：firstList = [], secondList = [[4,8],[10,12]]
//输出：[]
//
//
// 示例 4：
//
//
//输入：firstList = [[1,7]], secondList = [[3,10]]
//输出：[[3,7]]
//
//
//
//
// 提示：
//
//
// 0 <= firstList.length, secondList.length <= 1000
// firstList.length + secondList.length >= 1
// 0 <= starti < endi <= 109
// endi < starti+1
// 0 <= startj < endj <= 109
// endj < startj+1
//
// Related Topics 数组 双指针

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihua
 * @since 2021/11/12
 */
public class IntervalIntersection {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int f = 0, fLen = firstList.length;
        int s = 0, sLen = secondList.length;
        List<int[]> resultList = new ArrayList<>();
        while (f < fLen && s < sLen) {
            int f1 = firstList[f][0], f2 = firstList[f][1];
            int s1 = secondList[s][0], s2 = secondList[s][1];
            // 两个区间有交集的情况。由没有交集的情况取反得来：f2 < s1 || s2 < f1
            if (f2 >= s1 && s2 >= f1) {
                resultList.add(new int[]{Math.max(f1, s1), Math.min(f2, s2)});
            }
            // 按两个区间的右侧索引大小判断是否要递进到下一个区间
            if (f2 > s2) {
                s++;
            } else {
                f++;
            }
        }
        int resultSize = resultList.size();
        int[][] resultArr = new int[resultSize][];
        for (int i = 0; i < resultSize; i++) {
            resultArr[i] = resultList.get(i);
        }
        return resultArr;
    }

    public static void main(String[] args) {
        IntervalIntersection clazz = new IntervalIntersection();
        int[][] firstList = new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        // [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
        int[][] results = clazz.intervalIntersection(firstList, secondList);
        assert results.length == 6;
    }
}

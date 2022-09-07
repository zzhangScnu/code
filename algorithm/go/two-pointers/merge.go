package two_pointers

import (
	"sort"
)

//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
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
// 1 <= intervals.length <= 10⁴
// intervals[i].length == 2
// 0 <= starti <= endi <= 10⁴
//
// Related Topics 数组 排序

func merge(intervals [][]int) [][]int {
	if len(intervals) == 1 {
		return intervals
	}
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] <= intervals[j][0]
	})
	res := [][]int{{intervals[0][0], intervals[0][1]}}
	i := 1
	for i < len(intervals) {
		a := res[len(res)-1]
		a1, a2 := a[0], a[1]
		b1, b2 := intervals[i][0], intervals[i][1]
		if b1 >= a1 && b1 <= a2 {
			res[len(res)-1] = []int{min(a1, b1), max(a2, b2)}
		} else {
			res = append(res, []int{b1, b2})
		}
		i++
	}
	return res
}

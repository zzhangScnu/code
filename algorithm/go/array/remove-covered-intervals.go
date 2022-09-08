package main

import "sort"

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

func removeCoveredIntervals(intervals [][]int) int {
	l := len(intervals)
	if l == 1 {
		return 1
	}
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] <= intervals[j][0]
	})
	res := [][]int{{intervals[0][0], intervals[0][1]}}
	i := 1
	for i < l {
		a := res[len(res)-1]
		a1, a2 := a[0], a[1]
		b1, b2 := intervals[i][0], intervals[i][1]
		i++
		if b1 >= a1 && b2 <= a2 {
			continue
		}
		res = append(res, []int{b1, b2})
	}
	return len(res)
}

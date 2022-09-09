package main

import (
	"sort"
)

//给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重
//叠 。
//
//
//
// 示例 1:
//
//
//输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
//输出: 1
//解释: 移除 [1,3] 后，剩下的区间没有重叠。
//
//
// 示例 2:
//
//
//输入: intervals = [ [1,2], [1,2], [1,2] ]
//输出: 2
//解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
//
//
// 示例 3:
//
//
//输入: intervals = [ [1,2], [2,3] ]
//输出: 0
//解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
//
//
//
//
// 提示:
//
//
// 1 <= intervals.length <= 10⁵
// intervals[i].length == 2
// -5 * 10⁴ <= starti < endi <= 5 * 10⁴
//
// Related Topics 贪心 数组 动态规划 排序

func eraseOverlapIntervals(intervals [][]int) int {
	l := len(intervals)
	if l == 1 {
		return 0
	}
	sort.Slice(intervals, func(i, j int) bool { // 按结束时间排序，把buffer尽可能多地留给后来者
		return intervals[i][1] < intervals[j][1]
	})
	preR := intervals[0][1]
	res := 1 // i从1开始，即索引为0的区间已经算进结果集了
	i := 1
	for i < l {
		postL := intervals[i][0]
		if postL >= preR { // 注意这个条件，[1, 2][2, 3]不算重叠
			preR = intervals[i][1]
			res++
		}
		i++
	}
	return len(intervals) - res
}

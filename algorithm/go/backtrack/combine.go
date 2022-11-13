package backtrack

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
//
// Related Topics 回溯

func combine(n int, k int) [][]int {
	var res [][]int
	var track []int
	var dfs func(start int)
	dfs = func(start int) {
		if len(track) == k {
			res = append(res, append([]int{}, track...))
		}
		for i := start; i <= n; i++ {
			track = append(track, i)
			dfs(i + 1)
			track = track[:len(track)-1]
		}
	}
	dfs(1)
	return res
}

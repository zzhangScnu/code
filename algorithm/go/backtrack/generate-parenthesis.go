package backtrack

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：["()"]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
//
// Related Topics 字符串 动态规划 回溯

func generateParenthesis(n int) []string {
	size := 2 * n
	left, right := n, n
	var res []string
	var track string
	var dfs func(idx int)
	dfs = func(idx int) {
		if left > right || left < 0 || right < 0 {
			return
		}
		if idx == size && left == right { // left == 0 && right == 0，可以节省一个size的参数
			res = append(res, track)
			return
		}
		track += "("
		left--
		dfs(idx + 1) // left 和 right的自减结果可以通过dfs()的参数传递，这样回到这一层的时候，不需要显式地自增回来
		track = track[:len(track)-1]
		left++
		track += ")"
		right--
		dfs(idx + 1)
		track = track[:len(track)-1]
		right++
	}
	dfs(0)
	return res
}

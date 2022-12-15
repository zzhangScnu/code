package island

//给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
//。
//
//
//
//
//
//
//
// 示例 1：
//
//
//输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O",
//"X","X"]]
//输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的'O'都不会被填充为'X'。 任何不在边界上，或不与边界上的'O'相连的'O'最终都
//会被填充为'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
//
//
// 示例 2：
//
//
//输入：board = [["X"]]
//输出：[["X"]]
//
//
//
//
// 提示：
//
//
// m == board.length
// n == board[i].length
// 1 <= m, n <= 200
// board[i][j] 为 'X' 或 'O'
//
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵

func solve(board [][]byte) {
	tmpVal, replacedVal, targetVal := byte('1'), byte('O'), byte('X')
	directs := [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
	rowLen, colLen := len(board), len(board[0])
	var dfs func(i, j int, replaced, target byte)
	dfs = func(i, j int, replaced, target byte) {
		if i < 0 || j < 0 || i >= rowLen || j >= colLen || board[i][j] != replaced {
			return
		}
		board[i][j] = target
		for _, direct := range directs {
			dfs(i+direct[0], j+direct[1], replaced, target)
		}
	}
	for i := 0; i < colLen; i++ {
		dfs(0, i, replacedVal, tmpVal)
		dfs(rowLen-1, i, replacedVal, tmpVal)
	}
	for i := 0; i < rowLen; i++ {
		dfs(i, 0, replacedVal, tmpVal)
		dfs(i, colLen-1, replacedVal, tmpVal)
	}
	for i := 0; i < rowLen; i++ {
		for j := 0; j < colLen; j++ {
			if board[i][j] == replacedVal {
				dfs(i, j, replacedVal, targetVal)
			}
		}
	}
	for i := 0; i < rowLen; i++ {
		for j := 0; j < colLen; j++ {
			if board[i][j] == tmpVal {
				board[i][j] = replacedVal
			}
		}
	}
}

func solveFillSimply(board [][]byte) {
	tmpVal, replacedVal, targetVal := byte('1'), byte('O'), byte('X')
	directs := [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
	rowLen, colLen := len(board), len(board[0])
	var dfs func(i, j int)
	dfs = func(i, j int) {
		if i < 0 || j < 0 || i >= rowLen || j >= colLen || board[i][j] != replacedVal {
			return
		}
		board[i][j] = tmpVal
		for _, direct := range directs {
			dfs(i+direct[0], j+direct[1])
		}
	}
	for i := 0; i < colLen; i++ {
		dfs(0, i)
		dfs(rowLen-1, i)
	}
	for i := 0; i < rowLen; i++ {
		dfs(i, 0)
		dfs(i, colLen-1)
	}
	for i := 0; i < rowLen; i++ {
		for j := 0; j < colLen; j++ {
			if board[i][j] == replacedVal {
				// 这里已经不需要用dfs去填充了，直接遍历即可（根本原因是不用标记重复访问）
				board[i][j] = targetVal
				continue
			}
			if board[i][j] == tmpVal {
				board[i][j] = replacedVal
			}
		}
	}
}

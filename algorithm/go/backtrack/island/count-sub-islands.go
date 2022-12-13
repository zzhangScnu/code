package island

//给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 岛屿 是由 四个方向 （水平或者竖
//直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。
//
// 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那
//么我们称 grid2 中的这个岛屿为 子岛屿 。
//
// 请你返回 grid2 中 子岛屿 的 数目 。
//
//
//
// 示例 1：
// 输入：grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]],
//grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
//输出：3
//解释：如上图所示，左边为 grid1 ，右边为 grid2 。
//grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
//
//
// 示例 2：
// 输入：grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]],
//grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
//输出：2
//解释：如上图所示，左边为 grid1 ，右边为 grid2 。
//grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
//
//
//
//
// 提示：
//
//
// m == grid1.length == grid2.length
// n == grid1[i].length == grid2[i].length
// 1 <= m, n <= 500
// grid1[i][j] 和 grid2[i][j] 都要么是 0 要么是 1 。
//
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵

var directions = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

// 不能用哈希的方式去做，因为是子岛屿，不是相同的岛屿
// 0 （表示水域）和 1 （表示陆地）
func countSubIslands(grid1 [][]int, grid2 [][]int) int {
	rowLen, colLen := len(grid1), len(grid1[0])
	var flood func(grid [][]int, i, j int)
	flood = func(grid [][]int, i, j int) {
		if i < 0 || j < 0 || i >= rowLen || j >= colLen || grid[i][j] == 0 {
			return
		}
		grid[i][j] = 0
		for _, direction := range directions {
			flood(grid, i+direction[0], j+direction[1])
		}
	}
	for i := 0; i < rowLen; i++ {
		for j := 0; j < colLen; j++ {
			if grid1[i][j] == 0 && grid2[i][j] == 1 {
				flood(grid2, i, j)
			}
		}
	}
	res := 0
	for i := 0; i < rowLen; i++ {
		for j := 0; j < colLen; j++ {
			if grid2[i][j] == 1 {
				res++
				flood(grid2, i, j) // 这一步不能漏，要把已统计的岛屿淹没
			}
		}
	}
	return res
}

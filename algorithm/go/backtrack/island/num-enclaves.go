package island

//给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
//
// 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
//
// 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
//
//
//
// 示例 1：
//
//
//输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
//输出：3
//解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
//
//
// 示例 2：
//
//
//输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
//输出：0
//解释：所有 1 都在边界上或可以到达边界。
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 500
// grid[i][j] 的值为 0 或 1
//
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵

//  0 表示一个海洋单元格、1 表示一个陆地单元格
func numEnclaves(grid [][]int) int {
	directions := [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
	rowLen, colLen := len(grid), len(grid[0])
	var flood func(i, j int)
	flood = func(i, j int) {
		if i < 0 || j < 0 || i >= rowLen || j >= colLen {
			return
		}
		if grid[i][j] == 0 {
			return
		}
		grid[i][j] = 0
		for _, direction := range directions {
			flood(i+direction[0], j+direction[1])
		}
	}
	// 先将边缘的陆地都淹没
	for i := 0; i < rowLen; i++ {
		flood(i, 0)
		flood(i, colLen-1)
	}
	for j := 0; j < colLen; j++ {
		flood(0, j)
		flood(rowLen-1, j)
	}
	// 再数封闭的陆地数量
	res := 0
	for i := 0; i < rowLen; i++ {
		for j := 0; j < colLen; j++ {
			if grid[i][j] == 1 {
				res++
				// 这里不用淹没剩下的陆地了，因为要一一去数全部陆地的数量，而不是块数
			}
		}
	}
	return res
}

package island

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
//
// 此外，你可以假设该网格的四条边均被水包围。
//
//
//
// 示例 1：
//
//
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
//
//
// 示例 2：
//
//
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 300
// grid[i][j] 的值为 '0' 或 '1'
//
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵

// '1'（陆地）和 '0'（水）
func numIslands(grid [][]byte) int {
	rowLen, colLen := len(grid), len(grid[0])
	var flood func(i, j int)
	flood = func(i, j int) {
		if i < 0 || j < 0 || i >= rowLen || j >= colLen {
			return
		}
		if grid[i][j] == '0' { // 这里必须是字符0，byte -> uint -> rune
			return
		}
		grid[i][j] = '0'
		flood(i-1, j)
		flood(i+1, j)
		flood(i, j-1)
		flood(i, j+1)
	}
	res := 0
	for i := 0; i < rowLen; i++ {
		for j := 0; j < colLen; j++ {
			if grid[i][j] == '1' {
				res++
				flood(i, j)
			}
		}
	}
	return res
}

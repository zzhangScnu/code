package island

//二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
//
// 请返回 封闭岛屿 的数目。
//
//
//
// 示例 1：
//
//
//
//
//输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,
//0,1],[1,1,1,1,1,1,1,0]]
//输出：2
//解释：
//灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
//
// 示例 2：
//
//
//
//
//输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//输出：1
//
//
// 示例 3：
//
//
//输入：grid = [[1,1,1,1,1,1,1],
//            [1,0,0,0,0,0,1],
//            [1,0,1,1,1,0,1],
//            [1,0,1,0,1,0,1],
//            [1,0,1,1,1,0,1],
//            [1,0,0,0,0,0,1],
//            [1,1,1,1,1,1,1]]
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= grid.length, grid[0].length <= 100
// 0 <= grid[i][j] <=1
//
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵

// 0 （土地） 1 （水）
func closedIsland(grid [][]int) int {
	directions := [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
	rowLen, colLen := len(grid), len(grid[0])
	var flood func(i, j int)
	flood = func(i, j int) {
		if i < 0 || j < 0 || i >= rowLen || j >= colLen {
			return
		}
		if grid[i][j] == 1 {
			return
		}
		grid[i][j] = 1
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
	// 再寻找封闭的岛屿
	res := 0
	for i := 0; i < rowLen; i++ {
		for j := 0; j < colLen; j++ {
			if grid[i][j] == 0 {
				res++
				flood(i, j)
			}
		}
	}
	return res
}

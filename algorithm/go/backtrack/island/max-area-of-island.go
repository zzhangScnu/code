package island

//给你一个大小为 m x n 的二进制矩阵 grid 。
//
// 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都
//被 0（代表水）包围着。
//
// 岛屿的面积是岛上值为 1 的单元格的数目。
//
// 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
//
//
//
// 示例 1：
//
//
//输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,
//0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,
//0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//输出：6
//解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
//
//
// 示例 2：
//
//
//输入：grid = [[0,0,0,0,0,0,0,0]]
//输出：0
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 50
// grid[i][j] 为 0 或 1
//
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵

// 1-土地 0-海水
func maxAreaOfIslandWrong(grid [][]int) int {
	maxArea := 0
	rowLen, colLen := len(grid), len(grid[0])
	directions := [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
	var flood func(i, j, area int)
	flood = func(i, j, area int) {
		if i < 0 || j < 0 || i >= rowLen || j >= colLen {
			return
		}
		if grid[i][j] == 0 {
			return
		}
		grid[i][j] = 0
		area++
		if area > maxArea {
			maxArea = area
		}
		for _, direction := range directions {
			// 这样会导致算漏了某部分面积（在分岔路口向不同方法行进的时候，只会算到一个方向上的最大面积）
			flood(i+direction[0], j+direction[1], area)
		}
	}
	for i := 0; i < rowLen; i++ {
		for j := 0; j < colLen; j++ {
			if grid[i][j] == 1 {
				flood(i, j, 0)
			}
		}
	}
	return maxArea
}

// 1-土地 0-海水
// 拆分子问题，类似于后序遍历，拿到各个子问题的结果后再结合本身求解
func maxAreaOfIsland(grid [][]int) int {
	maxArea := 0
	rowLen, colLen := len(grid), len(grid[0])
	directions := [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
	var flood func(i, j int) int
	flood = func(i, j int) int {
		if i < 0 || j < 0 || i >= rowLen || j >= colLen {
			return 0
		}
		if grid[i][j] == 0 {
			return 0
		}
		grid[i][j] = 0
		// 当前块的面积
		area := 1
		// 往不同方向走的面积
		for _, direction := range directions {
			area += flood(i+direction[0], j+direction[1])
		}
		return area
	}
	for i := 0; i < rowLen; i++ {
		for j := 0; j < colLen; j++ {
			if grid[i][j] == 1 {
				area := flood(i, j)
				if area > maxArea {
					maxArea = area
				}
			}
		}
	}
	return maxArea
}

package island

import "strconv"

// 计算不同岛屿的数量
//样例 1:
//11000
//11000
//00011
//00011
//给定上图，返回结果 1。
//
//样例 2:
//11011
//10000
//00001
//11011
//给定上图，返回结果 3。
//
//注意:
//11
//1
//和
// 1
//11
//是不同的岛屿，因为我们不考虑旋转、翻转操作。
//
//注释 :  二维数组每维的大小都不会超过50。

var initialDirection = 666

func numDistinctIslands(grid [][]int) int {
	rowLen, colLen := len(grid), len(grid[0])
	mapping := make(map[string]bool, rowLen)
	for i := 0; i < rowLen; i++ {
		for j := 0; j < colLen; j++ {
			if grid[i][j] == 1 { // 如果是陆地才需要统计
				// 初始方向不重要，可以想象成空降到第一块陆地，之后才需要选取不同方向遍历
				routine := serialize(i, j, initialDirection, grid)
				mapping[routine] = true
			}
		}
	}
	return len(mapping)
}

func serialize(i, j int, directionIdx int, grid [][]int) string {
	rowLen, colLen := len(grid), len(grid[0])
	if i < 0 || j < 0 || i >= rowLen || j >= colLen {
		return ""
	}
	if grid[i][j] == 0 {
		return ""
	}
	grid[i][j] = 0
	// 记录进入的方向
	routine := strconv.Itoa(directionIdx) + ","
	// 记录向不同方向遍历时，产生的子路线
	for idx, direction := range directions {
		// idx表示上/下/左/右四个方向
		routine += serialize(i+direction[0], j+direction[1], idx, grid)
	}
	// 记录离开的方向
	routine += strconv.Itoa(-directionIdx) + ","
	return routine
}

package binary_search

//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//
//
// 每行中的整数从左到右按升序排列。
// 每行的第一个整数大于前一行的最后一个整数。
//
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
//
//
// 示例 2：
//
//
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 100
// -10⁴ <= matrix[i][j], target <= 10⁴
//
// Related Topics 数组 二分查找 矩阵

func searchMatrix(matrix [][]int, target int) bool {
	l := len(matrix)
	lo, hi, mi, val := 0, (l-1)*l+l, 0, 0
	for lo <= hi {
		mi = lo + (hi-lo)>>1
		val = matrix[mi/l][mi%l]
		if val == target {
			return true
		} else if val > target {
			hi = mi - 1
		} else {
			lo = mi + 1
		}
	}
	return false
}

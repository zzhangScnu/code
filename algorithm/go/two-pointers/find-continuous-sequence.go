package two_pointers

//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
//
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
//
//
//
// 示例 1：
//
// 输入：target = 9
//输出：[[2,3,4],[4,5]]
//
//
// 示例 2：
//
// 输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]
//
//
//
//
// 限制：
//
//
// 1 <= target <= 10^5
//
//
//
// Related Topics 数学 双指针 枚举

func findContinuousSequence(target int) [][]int {
	l, r := 1, 2 // 0不能算进去
	var res [][]int
	for l < r {
		var subs []int
		sum := calContinuousSum(l, r)
		if sum == target {
			for i := l; i <= r; i++ {
				subs = append(subs, i)
			}
			res = append(res, subs)
			l++ // l++后，sum必然小于target，会走到下面的小于分支，此时r++，增大r以逼近target。而不需要令r=l+1重新枚举
		} else if sum < target {
			r++
		} else { // 当前起点l已经不能满足有r使得总和等于target，需要枚举新的起点
			l++
		}
	}
	return res
}

func calContinuousSum(l, r int) int {
	return (l + r) * (r - l + 1) / 2
}

package backtrack

//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
// 示例 2：
//
//
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// nums 中的所有整数 互不相同
//
//
// Related Topics 数组 回溯

func permute(nums []int) [][]int {
	var res []int
	var results [][]int
	used := make([]bool, len(nums)) // 用used标记数组可以避免对nums频繁的去掉/加入候选元素
	var backtrack func()
	backtrack = func() {
		if len(res) == len(nums) {
			results = append(results, append([]int{}, res...)) // 这里要深拷贝一个新的slice
			return
		}
		for i, num := range nums {
			if used[i] {
				continue
			}
			res = append(res, num)
			used[i] = true
			backtrack()
			res = res[:len(res)-1]
			used[i] = false
		}
	}
	backtrack()
	return results
}

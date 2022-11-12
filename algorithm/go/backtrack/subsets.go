package backtrack

//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//
//
// 示例 2：
//
//
//输入：nums = [0]
//输出：[[],[0]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
// nums 中的所有元素 互不相同
//
//
// Related Topics 位运算 数组 回溯

func subsets(nums []int) [][]int {
	var res [][]int
	var track []int
	var dfs func(start int)
	dfs = func(start int) {
		res = append(res, append([]int{}, track...))
		for i := start; i < len(nums); i++ {
			track = append(track, nums[i])
			dfs(i + 1)
			track = track[:len(track)-1]
		}
	}
	dfs(0)
	return res
}

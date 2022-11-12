package backtrack

import "sort"

//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
//
//
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
//
//
// Related Topics 位运算 数组 回溯

func subsetsWithDup(nums []int) [][]int {
	// 不能做len==1的判断，因为还需要加上空集
	sort.Ints(nums)
	var res [][]int
	var track []int
	var dfs func(start int)
	dfs = func(start int) {
		res = append(res, append([]int{}, track...)) // 空集是第一次进入这里的时候加入结果集的
		for i := start; i < len(nums); i++ {
			if i != start && nums[i] == nums[i-1] { // 这里第一次写成了i != 0，实际上在子树的生成过程中，start不一定是0，画个图就清晰了
				continue
			}
			track = append(track, nums[i])
			dfs(i + 1)
			track = track[:len(track)-1]
		}
	}
	dfs(0)
	return res
}

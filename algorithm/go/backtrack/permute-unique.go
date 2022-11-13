package backtrack

import "sort"

//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 8
// -10 <= nums[i] <= 10
//
//
// Related Topics 数组 回溯

const EmptyNum = 11

func permuteUnique(nums []int) [][]int {
	if len(nums) == 1 {
		return [][]int{nums}
	}
	sort.Ints(nums)
	var res [][]int
	var track []int
	used := make([]bool, len(nums))
	var dfs func()
	dfs = func() {
		if len(track) == len(nums) {
			res = append(res, append([]int{}, track...))
			return
		}
		preNum := EmptyNum // 在每一层先初始化。需要在每层记录从左到右生成树枝的值，如果重复了就咔嚓
		// 因为如果有树枝重复，下面生成的全排列子树必然也是会重复的，所以不需要考虑了
		for i := 0; i < len(nums); i++ {
			// if used[i] == true || i != 0 && nums[i] == nums[i-1] { 不可以这样判断，否则1，2，2`这种组合就无法生成了
			// 因为nums不只是给同一层用的，会在整个纵深都用到
			if used[i] == true || nums[i] == preNum {
				continue
			}
			used[i] = true
			preNum = nums[i]
			track = append(track, nums[i])
			dfs()
			used[i] = false
			//preNum = EmptyNum 不需要
			track = track[:len(track)-1]
		}
	}
	dfs()
	return res
}

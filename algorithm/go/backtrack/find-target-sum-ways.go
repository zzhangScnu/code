package backtrack

import "fmt"

//给你一个整数数组 nums 和一个整数 target 。
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
//
//
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
//
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
//
//
// 示例 2：
//
//
//输入：nums = [1], target = 1
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 20
// 0 <= nums[i] <= 1000
// 0 <= sum(nums[i]) <= 1000
// -1000 <= target <= 1000
//
//
// Related Topics 数组 动态规划 回溯

func findTargetSumWays(nums []int, target int) int {
	size := len(nums)
	var res int
	var dfs func(idx int, trackSum int)
	dfs = func(idx int, trackSum int) {
		if idx == size {
			if trackSum == target { // 这两个条件不能写在同一个判断语句里，否则可能会导致无法return而进入下一层，进而数组越界
				res++
			}
			return // 无论满不满足结果集的要求，都要return了
		}
		//for i := idx; i < size; i++ { // 不需要for，因为一层其实就只有下面的+/-两种选择
		dfs(idx+1, trackSum+nums[idx])
		dfs(idx+1, trackSum-nums[idx])
		//}
	}
	dfs(0, 0)
	return res
}

// dp + 备忘录消除重叠子问题
func findTargetSumWaysDistinctly(nums []int, target int) int {
	size := len(nums)
	mapping := make(map[string]int, len(nums))
	// 在idx层，当结果是trackSum时，子树中有多少种解法
	var dfs func(idx int, trackSum int) int
	dfs = func(idx int, trackSum int) int {
		if idx == size {
			if trackSum == target {
				return 1
			}
			return 0
		}
		key := fmt.Sprintf("%v-%v", idx, trackSum)
		if val, ok := mapping[key]; ok {
			return val
		}
		// 站在某个节点上，结果等于子树的两条边的运算和
		val := dfs(idx+1, trackSum+nums[idx]) + dfs(idx+1, trackSum-nums[idx])
		mapping[key] = val
		return val
	}
	// 不需要维护一个res，dp依赖子问题的解法（返回值）
	return dfs(0, 0)
}

// todo：子集划分解法

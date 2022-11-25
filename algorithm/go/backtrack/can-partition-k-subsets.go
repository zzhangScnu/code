package backtrack

//给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
//
//
//
// 示例 1：
//
//
//输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
//
// 示例 2:
//
//
//输入: nums = [1,2,3,4], k = 3
//输出: false
//
//
//
// 提示：
//
//
// 1 <= k <= len(nums) <= 16
// 0 < nums[i] < 10000
// 每个元素的频率在 [1,4] 范围内
//
//
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩

func canPartitionKSubsets(nums []int, k int) bool {
	sum := 0
	for _, num := range nums {
		sum += num
	}
	if sum%k != 0 {
		return false
	}
	target := sum / k
	used := 0 // 位图。可以作为全局变量，因为每个数字最多只能用一次
	mapping := make(map[int]bool, len(nums))
	// bucketSum必须作为参数传递，因为每个桶的都不一样，需要跟bucketIdx绑定
	var dfs func(bucketIdx int, numIdx int, bucketSum int) bool
	dfs = func(bucketIdx int, numIdx int, bucketSum int) bool {
		if bucketIdx == k {
			return true
		}
		// 如果当前桶已经装满，继续往后判断下一个桶是否能满足条件
		if bucketSum == target {
			flag := dfs(bucketIdx+1, 0, 0)
			// 如果桶已经装满，记录一下当前的使用情况作为备忘录，避免重复子问题运算
			mapping[used] = flag
			return flag
		}
		if flag, ok := mapping[used]; ok {
			return flag
		}
		for i := numIdx; i < len(nums); i++ {
			if nums[i]+bucketSum > target {
				// 在这里剪枝而不是等下次递归再作为base case判断，少用了一个栈
				continue
			}
			// used的第i位记录了nums[i]是否已被使用过
			if (used>>i)&1 == 1 {
				continue
			}
			bucketSum += nums[i]
			used = used | (1 << i)
			// 对于当前桶，继续往后判断是否能装入剩余的数字
			if dfs(bucketIdx, i+1, bucketSum) {
				return true
			}
			bucketSum -= nums[i]
			used = used ^ (1 << i) // 按位异或
		}
		return false
	}
	return dfs(0, 0, 0)
}

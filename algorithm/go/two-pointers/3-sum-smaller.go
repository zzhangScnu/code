package two_pointers

import "sort"

//给定一个长度为 n 的整数数组和一个目标值 target，寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成立的三元组  i, j, k 个数（0 <= i < j < k < n）。
//
//示例：
//输入: nums = [-2,0,1,3], target = 2
//输出: 2
//解释: 因为一共有两个三元组满足累加和小于 2:
//     [-2,0,1]
//     [-2,0,3]
//进阶：是否能在 O(n2) 的时间复杂度内解决？

func threeSumSmaller(nums []int, target int) int {
	cnt := 0
	length := len(nums)
	sort.Ints(nums)
	for i := 0; i < length-2; i++ {
		if nums[i] >= target {
			break
		}
		remain := target - nums[i]
		l, h := i+1, length-1
		for l < h {
			if nums[l]+nums[h] < remain {
				// nums[l]和nums[l+1...h]间的任一元素的和都小于remain
				cnt += h - l
				l++
			} else {
				h--
			}
		}
	}
	return cnt
}

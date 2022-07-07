package main

//给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。
//
// 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
//
//
//
// 示例 1：
//
//
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4]
//
// 示例 2：
//
//
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1]
//
// 示例 3：
//
//
//输入：nums = [], target = 0
//输出：[-1,-1]
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 10⁵
// -10⁹ <= nums[i] <= 10⁹
// nums 是一个非递减数组
// -10⁹ <= target <= 10⁹
//
// Related Topics 数组 二分查找

func searchRange(nums []int, target int) []int {
	l, h := 0, len(nums)-1
	s, e := -1, -1 //边界条件，找不到返回该对
	for l <= h {
		mid := l + (h-l)/2 //这里经常没有加上l
		if nums[mid] > target {
			h = mid - 1
		} else if nums[mid] < target {
			l = mid + 1
		} else if nums[mid] == target {
			s = mid
			for s > 0 && nums[s-1] == target {
				s--
			}
			break
		}
	}
	e = s
	for e < len(nums)-1 && nums[e+1] == target {
		e++
	}
	return []int{s, e}
}

func searchRangeOptimize(nums []int, target int) []int {
	return []int{searchLowRange(nums, target), searchHighRange(nums, target)}
}

func searchLowRange(nums []int, target int) int {
	max := len(nums) - 1
	l, h := 0, max
	var mid int
	for l <= h {
		mid = l + (h-l)>>1
		if nums[mid] > target {
			h = mid - 1
		} else if nums[mid] < target {
			l = mid + 1
		} else if nums[mid] == target {
			if mid == 0 || nums[mid] > nums[mid-1] {
				return mid
			} else {
				h = mid - 1
			}
		}
	}
	return -1
}

func searchHighRange(nums []int, target int) int {
	max := len(nums) - 1
	l, h := 0, max
	var mid int
	for l <= h {
		mid = l + (h-l)>>1
		if nums[mid] > target {
			h = mid - 1
		} else if nums[mid] < target {
			l = mid + 1
		} else if nums[mid] == target {
			if mid == max || nums[mid] < nums[mid+1] {
				return mid
			} else {
				l = mid + 1
			}
		}
	}
	return -1
}

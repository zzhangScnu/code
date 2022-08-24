package two_pointers

import "sort"

//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
//
// 你可以按任意顺序返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
//
//
// 示例 2：
//
//
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
//
//
// 示例 3：
//
//
//输入：nums = [3,3], target = 6
//输出：[0,1]
//
//
//
//
// 提示：
//
//
// 2 <= nums.length <= 10⁴
// -10⁹ <= nums[i] <= 10⁹
// -10⁹ <= target <= 10⁹
// 只会存在一个有效答案
//
//
// 进阶：你可以想出一个时间复杂度小于 O(n²) 的算法吗？
// Related Topics 数组 哈希表

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
//
//
// 示例 1：
//
//
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = [0,1,1]
//输出：[]
//
//
// 示例 3：
//
//
//输入：nums = [0,0,0]
//输出：[[0,0,0]]
//
//
//
//
// 提示：
//
//
// 3 <= nums.length <= 3000
// -10⁵ <= nums[i] <= 10⁵
//
// Related Topics 数组 双指针 排序

func nSum(nums []int, n int, target int) [][]int {
	return doNSum(nums, n, target, 0)
}

func doNSum(nums []int, n int, target int, start int) [][]int {
	sort.Ints(nums)
	length, res := len(nums), [][]int{}
	if n == 2 { // base case写在循环外面
		res = append(res, twoSum(nums, target, start)...)
	} else {
		for i := start; i < length; i++ {
			firstNum := nums[i]
			numPairs := doNSum(nums, n-1, target-firstNum, i+1)
			for _, numPair := range numPairs {
				// nSum = (n-1)Sum + nums[i]
				res = append(res, append(numPair, firstNum))
			}
			for i+1 < length && firstNum == nums[i+1] { // 每次对i找完结果后，排除重复元素
				i++
			}
		}
	}
	return res
}

func twoSum(nums []int, target int, start int) [][]int {
	l, h := start, len(nums)-1
	res := [][]int{}
	for l < h {
		leftNum, rightNum := nums[l], nums[h]
		if leftNum+rightNum == target {
			res = append(res, []int{leftNum, rightNum})
			for l < h && leftNum == nums[l] {
				l++
			}
			for l < h && rightNum == nums[h] {
				h--
			}
		} else if leftNum+rightNum < target {
			for l < h && leftNum == nums[l] {
				l++
			}
		} else {
			for l < h && rightNum == nums[h] {
				h--
			}
		}
	}
	return res
}

// 有重复值的产生
func twoSumOfMap(nums []int, target int, start int) [][]int {
	counter := map[int]int{}
	res := [][]int{}
	for i := start; i < len(nums); i++ {
		num := nums[i]
		if _, ok := counter[target-num]; ok {
			res = append(res, []int{num, target - num})
		}
		counter[num] = i
	}
	return res
}

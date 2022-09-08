package sliding_window

import "math"

//给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <=
//t ，同时又满足 abs(i - j) <= k 。
//
// 如果存在则返回 true，不存在返回 false。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3,1], k = 3, t = 0
//输出：true
//
// 示例 2：
//
//
//输入：nums = [1,0,1,1], k = 1, t = 2
//输出：true
//
// 示例 3：
//
//
//输入：nums = [1,5,9,1,5,9], k = 2, t = 3
//输出：false
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 2 * 10⁴
// -2³¹ <= nums[i] <= 2³¹ - 1
// 0 <= k <= 10⁴
// 0 <= t <= 2³¹ - 1
//
// Related Topics 数组 桶排序 有序集合 排序 滑动窗口

// 是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。i <= k + j
func containsNearbyAlmostDuplicate(nums []int, k int, t int) bool {
	buckets := map[int]int{}
	bucketSize := t + 1 // t+1而不是t的原因
	for i, num := range nums {
		idx := num / bucketSize // todo：负数呢？
		if _, ok := buckets[idx]; ok {
			return true
		}
		buckets[idx] = num
		if leftNum, ok := buckets[idx-1]; ok && absSmaller(leftNum, num, t) {
			return true
		}
		if rightNum, ok := buckets[idx+1]; ok && absSmaller(num, rightNum, t) {
			return true
		}
		if i >= k { // 下一步处理i+1，需要将右侧元素移入，左侧元素踢出。假设此时j=0，满足的条件变成i+1<=k，即不满足的条件变成i>k-1，即i>=k
			idx = nums[i-k] / bucketSize
			delete(buckets, idx)
		}
	}
	return false
}

func absSmaller(i int, j int, t int) bool {
	return math.Abs(float64(i-j)) <= float64(t)
}

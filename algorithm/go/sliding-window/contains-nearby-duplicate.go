package sliding_window

//给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i
//- j) <= k 。如果存在，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3,1], k = 3
//输出：true
//
// 示例 2：
//
//
//输入：nums = [1,0,1,1], k = 1
//输出：true
//
// 示例 3：
//
//
//输入：nums = [1,2,3,1,2,3], k = 2
//输出：false
//
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -10⁹ <= nums[i] <= 10⁹
// 0 <= k <= 10⁵
//
// Related Topics 数组 哈希表 滑动窗口

func containsNearbyDuplicate(nums []int, k int) bool {
	length := len(nums)
	if k > length {
		return false
	}
	l, r := 0, k // 前闭后开
	for r <= len(nums) {
		l = r - k
		for l <= r-1 {
			if nums[l] == nums[r-1] {
				return true
			}
			l++
		}
		r++
	}
	return false
}

func containsNearbyDuplicateMap(nums []int, k int) bool {
	window := map[int]int{}
	l := 0
	for r := 0; r < len(nums); r++ {
		if r > k {
			l = r - k - 1
			window[nums[l]]--
		}
		if window[nums[r]] != 0 {
			return true
		}
		window[nums[r]]++
	}
	return false
}

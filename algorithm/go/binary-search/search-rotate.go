package binary_search

//整数数组 nums 按升序排列，数组中的值 互不相同 。
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[
//k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2
//,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
//
// 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
//
//
//
// 示例 1：
//
//
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
//
//
// 示例 2：
//
//
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1
//
// 示例 3：
//
//
//输入：nums = [1], target = 0
//输出：-1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 5000
// -10⁴ <= nums[i] <= 10⁴
// nums 中的每个值都 独一无二
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转
// -10⁴ <= target <= 10⁴
//
// Related Topics 数组 二分查找

func searchRotate(nums []int, target int) int {
	l := len(nums)
	lo, hi, mi := 0, l-1, 0
	for lo <= hi {
		mi = lo + (hi-lo)>>1
		if nums[mi] == target {
			return mi
		}
		// 左侧数组仍是升序
		if nums[0] <= nums[mi] {
			// 目标值在左侧数组中
			if nums[0] <= target && target < nums[mi] {
				// 继续在左侧寻找
				hi = mi - 1
			} else { // 否则在右侧寻找
				lo = mi + 1
			}
		} else { // 左侧已经被旋转打乱
			if nums[mi] < target && target <= nums[l-1] {
				// 继续在右侧寻找
				lo = mi + 1
			} else {
				hi = mi - 1
			}
		}
	}
	return -1
}

package two_pointers

import (
	"algorithm.com/structure"
	"container/heap"
	"sort"
)

//给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数
//目来描述。
//
// 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
//
//
//
// 示例 1：
//
//
//输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
//输出：[2,11,7,15]
//
//
// 示例 2：
//
//
//输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
//输出：[24,32,8,12]
//
//
//
//
// 提示：
//
//
// 1 <= nums1.length <= 10⁵
// nums2.length == nums1.length
// 0 <= nums1[i], nums2[i] <= 10⁹
//
// Related Topics 贪心 数组 排序

// 堆排序做法
func advantageCount(nums1 []int, nums2 []int) []int {
	sort.Ints(nums1)
	length := len(nums1)
	l, r := 0, length-1
	pq := newPriorityQueue(nums2)
	res := make([]int, length)
	for pq.Len() > 0 {
		item2 := heap.Pop(pq).(*structure.Item) // 注意，不是pq.Pop()，这个方法还要对堆进行重排序的
		if item2.Val < nums1[r] {
			res[item2.Index] = nums1[r]
			r--
		} else {
			res[item2.Index] = nums1[l]
			l++
		}
	}
	return res
}

func newPriorityQueue(nums []int) *structure.PriorityQueue {
	pq := make(structure.PriorityQueue, len(nums))
	for i, num := range nums {
		pq[i] = &structure.Item{
			Val:   num,
			Index: i,
		}
	}
	heap.Init(&pq)
	return &pq
}

// 直接排序做法
func advantageCountBySort(nums1 []int, nums2 []int) []int {
	length := len(nums1)
	sort.Ints(nums1)
	structNums := make([][]int, length)
	for i, num := range nums2 {
		structNums[i] = []int{i, num}
	}
	sort.Slice(structNums, func(i, j int) bool {
		return structNums[i][1] > structNums[j][1]
	})
	l, r := 0, length-1
	res := make([]int, length)
	for _, sn := range structNums {
		if sn[1] < nums1[r] {
			res[sn[0]] = nums1[r]
			r--
		} else {
			res[sn[0]] = nums1[l]
			l++
		}
	}
	return res
}

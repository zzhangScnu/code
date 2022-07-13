package binary_search

import "sort"

//给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
//
// 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
//
// 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
//
// 注意：不允许旋转信封。
//
//
// 示例 1：
//
//
//输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出：3
//解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
//
// 示例 2：
//
//
//输入：envelopes = [[1,1],[1,1],[1,1]]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= envelopes.length <= 10⁵
// envelopes[i].length == 2
// 1 <= wi, hi <= 10⁵
//
// Related Topics 数组 二分查找 动态规划 排序

func maxEnvelopesByLis(envelopes [][]int) int {
	if len(envelopes) == 0 {
		return 0
	}
	sort.Slice(envelopes, func(i, j int) bool {
		x, y := envelopes[i], envelopes[j]
		return x[0] < y[0] ||
			x[0] == y[0] && x[1] > y[1]
	})
	lis, target := []int{}, 0
	for _, envelope := range envelopes {
		target = envelope[1]
		idx := searchFirstBiggerThan(lis, target)
		if idx == -1 {
			continue
		} else if idx < len(lis) {
			lis[idx] = target
		} else {
			lis = append(lis, target)
		}
	}
	return len(lis)
}

func searchFirstBiggerThan(lis []int, target int) int {
	max := len(lis) - 1
	l, h, m := 0, max, 0
	for l <= h {
		m = l + (h-l)>>1
		if lis[m] < target {
			l = m + 1
		} else if lis[m] >= target { //由于最后是用h来判断是否所有元素都小于target，等于的分支需要放在这里，才能合适地移动h
			if m == 0 || lis[m-1] < target {
				return m
			}
			h = m - 1
		}
	}
	if h == max {
		return max + 1
	}
	return -1
}

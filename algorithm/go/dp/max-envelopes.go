package dp

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

// dp[i]: i代表0-i个信封中可以套娃的信封数量(当以i索引的信封做顶时的结果)
func maxEnvelopesByDp(envelopes [][]int) int {
	l := len(envelopes)
	if l == 1 {
		return 1
	}
	sort.Slice(envelopes, func(i, j int) bool {
		if envelopes[i][0] == envelopes[j][0] {
			return envelopes[i][1] < envelopes[j][1]
		}
		return envelopes[i][0] < envelopes[j][0]
	})
	dp := make([]int, l)
	ans := 0
	for i := 0; i < l; i++ {
		max := 0
		for j := 0; j < i; j++ {
			if canPutOn(envelopes[j], envelopes[i]) {
				if dp[j] > max {
					max = dp[j]
				}
			}
		}
		dp[i] = max + 1
		if dp[i] > ans {
			ans = dp[i]
		}
	}
	return ans
}

func canPutOn(e1 []int, e2 []int) bool {
	return e1[0] < e2[0] && e1[1] < e2[1]
}

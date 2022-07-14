package binary_search

//传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
//
// 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最
//大运载重量。
//
// 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
//
//
//
// 示例 1：
//
//
//输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
//输出：15
//解释：
//船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
//第 1 天：1, 2, 3, 4, 5
//第 2 天：6, 7
//第 3 天：8
//第 4 天：9
//第 5 天：10
//
//请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (1
//0) 是不允许的。
//
//
// 示例 2：
//
//
//输入：weights = [3,2,2,4,1,4], days = 3
//输出：6
//解释：
//船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
//第 1 天：3, 2
//第 2 天：2, 4
//第 3 天：1, 4
//
//
// 示例 3：
//
//
//输入：weights = [1,2,3,1,1], days = 4
//输出：3
//解释：
//第 1 天：1
//第 2 天：2
//第 3 天：3
//第 4 天：1, 1
//
//
//
//
// 提示：
//
//
// 1 <= days <= weights.length <= 5 * 10⁴
// 1 <= weights[i] <= 500
//
// Related Topics 数组 二分查找

func shipWithinDays(weights []int, days int) int {
	l, h := findMinAndMax(weights)
	m := 0
	// 查找小于等于days的最大值（左侧边界）
	for l <= h {
		m = l + (h-l)>>1
		actualDays := f(m, weights)
		if actualDays > days { // 实际天数比要求天数大，增加运载能力
			l = m + 1
		} else if actualDays <= days {
			h = m - 1
		}
	}
	return l // 找左侧边界，返回l；找右侧边界，返回h
}

func findMinAndMax(weights []int) (int, int) {
	min, max := 0, 0
	for _, weight := range weights {
		max += weight
		if weight > min {
			min = weight
		}
	}
	return min, max
}

// 当运输能力为x时，f(x)为运输天数
func f(x int, weights []int) int {
	days, remain, l := 0, x, len(weights)
	for i := 0; i < l; {
		remain = x
		for i < l {
			if remain >= weights[i] {
				remain -= weights[i]
				i++
			} else {
				break
			}
		}
		days++
	}
	return days
}

package binary_search

//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
//
// 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而
//"aec"不是）。
//
// 进阶：
//
// 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代
//码？
//
// 致谢：
//
// 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
//
//
//
// 示例 1：
//
//
//输入：s = "abc", t = "ahbgdc"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "axc", t = "ahbgdc"
//输出：false
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 100
// 0 <= t.length <= 10^4
// 两个字符串都只由小写字符组成。
//
// Related Topics 双指针 字符串 动态规划

func isSubsequence(s string, t string) bool {
	tMapping, ti := mappedTarget(t), 0
	for si := 0; si < len(s); si++ {
		arr, ok := tMapping[s[si]]
		if !ok {
			return false
		}
		idx := findFirstGE(arr, ti)
		if idx == len(arr) {
			return false
		}
		ti = arr[idx] + 1
	}
	return true
}

func findFirstGE(indexes []int, ti int) int {
	l, h, m := 0, len(indexes)-1, 0
	for l <= h {
		m = l + (h-l)>>1
		if indexes[m] >= ti {
			h = m - 1
		} else if indexes[m] < ti {
			l = m + 1
		}
	}
	return l
}

func mappedTarget(t string) map[uint8][]int {
	res := make(map[uint8][]int, 128)
	for i := 0; i < len(t); i++ {
		res[t[i]] = append(res[t[i]], i)
	}
	return res
}

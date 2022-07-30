package sliding_window

//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
//
//
//
// 示例 1:
//
//
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
//
//
// 示例 2:
//
//
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
//
//
//
//
// 提示:
//
//
// 1 <= s.length, p.length <= 3 * 10⁴
// s 和 p 仅包含小写字母
//
// Related Topics 哈希表 字符串 滑动窗口

func findAnagrams(s string, p string) []int {
	target := make(map[uint8]int)
	for i := range p {
		target[p[i]]++
	}
	l, r, valid, required, windowSize := 0, 0, 0, len(target), len(p)
	window := make(map[uint8]int)
	var res []int
	for r < len(s) {
		window[s[r]]++
		if window[s[r]] == target[s[r]] {
			valid++
		}
		r++
		for r-l >= windowSize { // 前闭后开
			if valid == required {
				res = append(res, l)
			}
			if window[s[l]] == target[s[l]] {
				valid--
			}
			window[s[l]]--
			l++
		}
	}
	return res
}

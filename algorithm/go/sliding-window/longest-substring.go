package sliding_window

//给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
//
//
//
// 示例 1：
//
//
//输入：s = "aaabb", k = 3
//输出：3
//解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
//
//
// 示例 2：
//
//
//输入：s = "ababbc", k = 2
//输出：5
//解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
//
//
//
// 提示：
//
//
// 1 <= s.length <= 10⁴
// s 仅由小写英文字母组成
// 1 <= k <= 10⁵
//
// Related Topics 哈希表 字符串 分治 滑动窗口

func longestSubstring(s string, k int) int {
	res, length := 0, len(s)
	for total := 1; total <= 26; total++ {
		l, r, valid, windowTotal := 0, 0, 0, 0
		m := map[uint8]int{}
		for r < length {
			if m[s[r]] == 0 {
				windowTotal++
			}
			m[s[r]]++
			if m[s[r]] == k {
				valid++
			}
			r++ // 注意累加的时机
			for windowTotal > total {
				if m[s[l]] == k {
					valid--
				}
				m[s[l]]--
				if m[s[l]] == 0 {
					windowTotal--
				}
				l++
			}
			if valid == total {
				res = max(res, r-l)
			}
		}
	}
	return res
}

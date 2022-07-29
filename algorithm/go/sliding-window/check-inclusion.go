package sliding_window

//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
//
// 换句话说，s1 的排列之一是 s2 的 子串 。
//
//
//
// 示例 1：
//
//
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
//
//
// 示例 2：
//
//
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= s1.length, s2.length <= 10⁴
// s1 和 s2 仅包含小写字母
//
// Related Topics 哈希表 双指针 字符串 滑动窗口

// s1 = "ab" s2 = "eidbaooo"
func checkInclusion(s1 string, s2 string) bool {
	target := make(map[uint8]int)
	for i := range s1 {
		target[s1[i]]++
	}
	l, r, windowSize := 0, 0, len(s1)
	window, valid, required := make(map[uint8]int), 0, len(target)
	for r < len(s2) {
		window[s2[r]]++
		if window[s2[r]] == target[s2[r]] {
			valid++
		}
		r++
		for r-l == windowSize {
			if valid == required {
				return true
			}
			if window[s2[l]] == target[s2[l]] {
				valid--
			}
			window[s2[l]]--
			l++
		}
	}
	return false
}

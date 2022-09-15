package two_pointers

//给你一个字符串 s，找到 s 中最长的回文子串。
//
//
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母组成
//
// Related Topics 字符串 动态规划

func longestPalindrome(s string) string {
	res := ""
	for i := range s {
		odd := findLongestPalindrome(s, i, i)
		even := findLongestPalindrome(s, i, i+1)
		if len(odd) > len(res) {
			res = odd
		}
		if len(even) > len(res) {
			res = even
		}
	}
	return res
}

func findLongestPalindrome(s string, l, r int) string {
	for l >= 0 && // todo
		r < len(s) &&
		s[l] == s[r] {
		l--
		r++
	}
	return s[l+1 : r] // todo
}

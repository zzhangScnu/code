package base

//给定一个包含大写字母和小写字母的字符串
// s ，返回 通过这些字母构造成的 最长的回文串 。
//
// 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
//
//
//
// 示例 1:
//
//
//输入:s = "abccccdd"
//输出:7
//解释:
//我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
//
//
// 示例 2:
//
//
//输入:s = "a"
//输入:1
//
//
// 示例 3：
//
//
//输入:s = "aaaaaccc"
//输入:7
//
// 提示:
//
//
// 1 <= s.length <= 2000
// s 只由小写 和/或 大写英文字母组成
//
//
// Related Topics 贪心 哈希表 字符串

func longestPalindrome(s string) int {
	mapping := make(map[rune]int, len(s)) // 直接用rune[]会更好
	for _, si := range s {
		mapping[si] += 1
	}
	length := 0
	usedSingle := false
	for _, cnt := range mapping {
		if cnt%2 == 0 {
			length += cnt
		} else if !usedSingle {
			length += cnt
			usedSingle = true
		} else {
			length += cnt / 2 * 2
		}
	}
	return length
}

func longestPalindromeForBest(s string) int {
	res := 0
	counter := make([]int, 128) // 大写+小写
	for _, si := range s {
		counter[si]++
		if counter[si]%2 == 0 {
			res += counter[si]
			counter[si] = 0
		}
	}
	if res < len(s) {
		res++
	}
	return res
}

package stack

import "container/list"

//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
//
//
//
//
//
// 示例 1：
//
//
//
//
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
//
//
// 示例 2：
//
//
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
//
//
// 示例 3：
//
//
//输入：s = ""
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 3 * 10⁴
// s[i] 为 '(' 或 ')'
//
//
// Related Topics 栈 字符串 动态规划

// ()(()) 这种也算有效括号，正解为6
func longestValidParentheses(s string) int {
	// cnt[i]是以s[i-1]为结尾的最长合法括号子串长度（此时s[i-1]一定是个右括号）
	cnt := make([]int, len(s)+1)
	stack := list.New()
	for i, ch := range s {
		if ch == '(' {
			stack.PushBack(i)
		} else { // ')'
			if stack.Len() == 0 {
				continue
			}
			leftIndex := stack.Back().Value.(int)
			cnt[i+1] = cnt[leftIndex] + i - leftIndex + 1
			stack.Remove(stack.Back())
		}
	}
	return getMax(cnt)
}

func getMax(cnt []int) int {
	m := 0
	for _, num := range cnt {
		if num > m {
			m = num
		}
	}
	return m
}

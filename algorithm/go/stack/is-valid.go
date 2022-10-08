package stack

import (
	"container/list"
)

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
// 每个右括号都有一个对应的相同类型的左括号。
//
//
//
//
// 示例 1：
//
//
//输入：s = "()"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "()[]{}"
//输出：true
//
//
// 示例 3：
//
//
//输入：s = "(]"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 10⁴
// s 仅由括号 '()[]{}' 组成
//
//
// Related Topics 栈 字符串

func isValid(s string) bool {
	if len(s) == 1 {
		return false
	}
	// Package list implements a doubly linked list
	stack := list.New()
	for _, ch := range s {
		if ch == '(' || ch == '{' || ch == '[' {
			stack.PushBack(ch)
			continue
		}
		if stack.Len() == 0 {
			return false
		}
		e := stack.Back()
		if mapping[e.Value.(int32)] == ch {
			stack.Remove(e)
		} else {
			return false
		}
	}
	if stack.Len() == 0 {
		return true
	}
	return false
}

var mapping = map[rune]rune{
	'(': ')',
	'[': ']',
	'{': '}',
}

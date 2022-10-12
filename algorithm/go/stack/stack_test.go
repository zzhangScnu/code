package stack

import (
	. "algorithm.com/structure"
	"testing"
)

func TestIsValid(t *testing.T) {
	Validate(isValid("(])"), false, t)
}

func TestMinAddToMakeValid(t *testing.T) {
	Validate(minAddToMakeValid("())"), 1, t)
	Validate(minAddToMakeValid("((("), 3, t)
	Validate(minAddToMakeValid(""), 0, t)
	Validate(minAddToMakeValid("()))(("), 4, t)
}

func TestMinInsertions(t *testing.T) {
	Validate(minInsertions("(()))"), 1, t)
	Validate(minInsertions("())"), 0, t)
	Validate(minInsertions("))())("), 3, t)
	Validate(minInsertions("(((((("), 12, t)
	Validate(minInsertions(")))))))"), 5, t)
}

func TestLongestValidParentheses(t *testing.T) {
	Validate(longestValidParentheses("()(())"), 6, t)
	Validate(longestValidParentheses("(()"), 2, t)
	Validate(longestValidParentheses(")()())"), 4, t)
	Validate(longestValidParentheses(""), 0, t)
	Validate(longestValidParentheses("("), 0, t)
	Validate(longestValidParentheses(")())()()"), 4, t)
}

func TestSimplifyTest(t *testing.T) {
	Validate(simplifyPath("/a/./b/../../c/"), "/c", t)
	Validate(simplifyPath("/home//foo/"), "/home/foo", t)
}

func TestEvalRPN(t *testing.T) {
	Validate(evalRPN([]string{"2", "1", "+", "3", "*"}), 9, t)
	Validate(evalRPN([]string{"4", "13", "5", "/", "+"}), 6, t)
	Validate(evalRPN([]string{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}), 22, t)
}

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

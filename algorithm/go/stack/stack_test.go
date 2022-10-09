package stack

import (
	. "algorithm.com/structure"
	"testing"
)

func TestIsValid(t *testing.T) {
	Validate(isValid("(])"), false, t)
}

func TestMinAddToMakeValid(t *testing.T) {
	//Validate(minAddToMakeValid("())"), 1, t)
	//Validate(minAddToMakeValid("((("), 3, t)
	//Validate(minAddToMakeValid(""), 0, t)
	Validate(minAddToMakeValid("()))(("), 4, t)
}

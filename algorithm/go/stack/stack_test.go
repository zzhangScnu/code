package stack

import (
	. "algorithm.com/structure"
	"testing"
)

func TestIsValid(t *testing.T) {
	Validate(isValid("(])"), false, t)
}

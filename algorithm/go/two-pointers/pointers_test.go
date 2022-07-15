package two_pointers

import (
	"algorithm.com/structure"
	"testing"
)

func TestIsSubSequence(t *testing.T) {
	structure.Validate(isSubsequence("abc", "ahbgdc"), true, t)
	structure.Validate(isSubsequence("axc", "ahbgdc"), false, t)
}

package backtrack

import (
	. "algorithm.com/structure"
	"testing"
)

func TestPermute(t *testing.T) {
	res := permute([]int{1, 2, 3})
	Validate(len(res), 6, t)
}

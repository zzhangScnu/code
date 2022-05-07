package bit

import (
	"algorithm.com/structure"
	"testing"
)

func TestSingleNumber(t *testing.T) {
	structure.Validate(SingleNumber([]int{2, 2, 1}), 1, t)
	structure.Validate(SingleNumber([]int{4, 1, 2, 1, 2}), 4, t)
}

func TestSingleNumberII(t *testing.T) {
	structure.Validate(SingleNumberII([]int{2, 2, 3, 2}), 3, t)
	structure.Validate(SingleNumberII([]int{0, 1, 0, 1, 0, 1, 99}), 99, t)
}

package bit

import (
	"algorithm.com/structure"
	"testing"
)

func TestSingleNumber(t *testing.T) {
	structure.Validate(SingleNumber([]int{2, 2, 1}), 1, t)
	structure.Validate(SingleNumber([]int{4, 1, 2, 1, 2}), 4, t)
}

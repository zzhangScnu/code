package pre_sum

import (
	. "algorithm.com/structure"
	"testing"
)

func TestSumRegion(t *testing.T) {
	numMatrix := Constructor1([][]int{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}})
	Validate(numMatrix.SumRegion(2, 1, 4, 3), 8, t)
}

package binary_search

import (
	"algorithm.com/structure"
	"testing"
)

func TestMaxEnvelopesDp(t *testing.T) {
	res := maxEnvelopesByLis([][]int{{5, 4}, {6, 4}, {6, 7}, {2, 3}})
	structure.Validate(res, 3, t)
	res = maxEnvelopesByLis([][]int{{1, 1}, {1, 1}, {1, 1}})
	structure.Validate(res, 1, t)
}

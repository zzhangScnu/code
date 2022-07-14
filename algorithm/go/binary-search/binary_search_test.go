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

func TestShipWithinDays(t *testing.T) {
	structure.Validate(shipWithinDays([]int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5), 15, t)
	structure.Validate(shipWithinDays([]int{3, 2, 2, 4, 1, 4}, 3), 6, t)
	structure.Validate(shipWithinDays([]int{1, 2, 3, 1, 1}, 4), 3, t)
}

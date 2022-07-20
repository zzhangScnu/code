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

func TestEatingMinSpeed(t *testing.T) {
	structure.Validate(minEatingSpeed([]int{3, 6, 7, 11}, 8), 4, t)
	structure.Validate(minEatingSpeed([]int{30, 11, 23, 4, 20}, 5), 30, t)
	structure.Validate(minEatingSpeed([]int{30, 11, 23, 4, 20}, 6), 23, t)
}

func TestIsSubSequence(t *testing.T) {
	structure.Validate(isSubsequence("aaaaaa", "bbaaaa"), false, t)
	structure.Validate(isSubsequence("abc", "ahbgdc"), true, t)
	structure.Validate(isSubsequence("axc", "ahbgdc"), false, t)
}

func TestSearchMatrix(t *testing.T) {
	structure.Validate(searchMatrix([][]int{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3), true, t)
	structure.Validate(searchMatrix([][]int{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13), false, t)
}

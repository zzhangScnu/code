package binary_search

import (
	. "algorithm.com/structure"
	"fmt"
	"testing"
)

func TestSearchRange(t *testing.T) {
	res := searchRangeOptimize([]int{5, 7, 7, 8, 8, 10}, 6)
	ValidateDeep(res, []int{-1, -1}, t)
	res = searchRangeOptimize([]int{}, 0)
	ValidateDeep(res, []int{-1, -1}, t)
}

func TestSearchInsert(t *testing.T) {
	res := searchInsert([]int{1, 3, 5, 6}, 5)
	Validate(res, 2, t)
	res = searchInsert([]int{1, 3, 5, 6}, 2)
	Validate(res, 1, t)
	res = searchInsert([]int{1, 3, 5, 6}, 7)
	Validate(res, 4, t)
	res = searchInsert([]int{1, 3, 5, 6}, 0)
	Validate(res, 0, t)
}

func TestMaxEnvelopesDp(t *testing.T) {
	res := maxEnvelopesByLis([][]int{{5, 4}, {6, 4}, {6, 7}, {2, 3}})
	Validate(res, 3, t)
	res = maxEnvelopesByLis([][]int{{1, 1}, {1, 1}, {1, 1}})
	Validate(res, 1, t)
}

func TestShipWithinDays(t *testing.T) {
	Validate(shipWithinDays([]int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5), 15, t)
	Validate(shipWithinDays([]int{3, 2, 2, 4, 1, 4}, 3), 6, t)
	Validate(shipWithinDays([]int{1, 2, 3, 1, 1}, 4), 3, t)
}

func TestEatingMinSpeed(t *testing.T) {
	Validate(minEatingSpeed([]int{3, 6, 7, 11}, 8), 4, t)
	Validate(minEatingSpeed([]int{30, 11, 23, 4, 20}, 5), 30, t)
	Validate(minEatingSpeed([]int{30, 11, 23, 4, 20}, 6), 23, t)
}

func TestIsSubSequence(t *testing.T) {
	Validate(isSubsequence("aaaaaa", "bbaaaa"), false, t)
	Validate(isSubsequence("abc", "ahbgdc"), true, t)
	Validate(isSubsequence("axc", "ahbgdc"), false, t)
}

func TestSearchMatrix(t *testing.T) {
	Validate(searchMatrix([][]int{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3), true, t)
	Validate(searchMatrix([][]int{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13), false, t)
	Validate(searchMatrix([][]int{{1}}, 2), false, t)
	Validate(searchMatrix([][]int{{1, 3}}, 3), true, t)
}

func TestSearchMatrixII(t *testing.T) {
	Validate(searchMatrixII([][]int{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5), true, t)
	Validate(searchMatrixII([][]int{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20), false, t)
	Validate(searchMatrixII([][]int{{1, 1}}, 0), false, t)
}

func TestSearchRotate(t *testing.T) {
	Validate(searchRotate([]int{4, 5, 6, 7, 0, 1, 2}, 0), 4, t)
	Validate(searchRotate([]int{4, 5, 6, 7, 0, 1, 2}, 3), -1, t)
	Validate(searchRotate([]int{1}, 0), -1, t)
}

func TestSearchRotateII(t *testing.T) {
	Validate(searchRotateII([]int{3, 1, 2, 3, 3, 3, 3}, 2), true, t)
	Validate(searchRotateII([]int{1, 0, 1, 1, 1}, 0), true, t)
}

func TestFindPeakElement(t *testing.T) {
	Validate(findPeakElement([]int{1, 2, 3, 1}), 2, t)
	Validate(findPeakElement([]int{1}), 0, t)
	fmt.Println(findPeakElement([]int{1, 2, 1, 3, 5, 6, 4})) // 1 or 5
}

func TestIsBadVersion(t *testing.T) {
	Validate(firstBadVersion(5), 4, t)
}

package main

import (
	"algorithm.com/structure"
	"testing"
)

func TestRemoveCoveredIntervals(t *testing.T) {
	structure.Validate(removeCoveredIntervals([][]int{{1, 4}, {3, 6}, {2, 8}}), 2, t)
}

func TestSummaryRanges(t *testing.T) {
	structure.ValidateDeep(summaryRanges([]int{0, 1, 2, 4, 5, 7}), []string{"0->2", "4->5", "7"}, t)
	structure.ValidateDeep(summaryRanges([]int{0, 2, 3, 4, 6, 8, 9}), []string{"0", "2->4", "6", "8->9"}, t)
}

func TestEraseOverlapIntervals(t *testing.T) {
	structure.Validate(eraseOverlapIntervals([][]int{{1, 2}, {2, 3}, {3, 4}, {1, 3}}), 1, t)
	structure.Validate(eraseOverlapIntervals([][]int{{1, 2}, {1, 2}, {1, 2}}), 2, t)
	structure.Validate(eraseOverlapIntervals([][]int{{1, 2}, {2, 3}}), 0, t)
	structure.Validate(eraseOverlapIntervals([][]int{{1, 100}, {11, 22}, {1, 11}, {2, 12}}), 2, t)
	structure.Validate(eraseOverlapIntervals([][]int{{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49}, {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}}), 7, t)

}

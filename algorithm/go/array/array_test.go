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

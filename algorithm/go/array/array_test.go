package main

import (
	"algorithm.com/structure"
	"testing"
)

func TestRemoveCoveredIntervals(t *testing.T) {
	structure.Validate(removeCoveredIntervals([][]int{{1, 4}, {3, 6}, {2, 8}}), 2, t)
}

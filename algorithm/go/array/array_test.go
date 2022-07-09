package main

import (
	"algorithm.com/structure"
	"testing"
)

func TestSearchRange(t *testing.T) {
	res := searchRangeOptimize([]int{5, 7, 7, 8, 8, 10}, 6)
	structure.ValidateDeep(res, []int{-1, -1}, t)
	res = searchRangeOptimize([]int{}, 0)
	structure.Validate(res, []int{-1, -1}, t)
}

func TestSearchInsert(t *testing.T) {
	res := searchInsert([]int{1, 3, 5, 6}, 5)
	structure.Validate(res, 2, t)
	res = searchInsert([]int{1, 3, 5, 6}, 2)
	structure.Validate(res, 1, t)
	res = searchInsert([]int{1, 3, 5, 6}, 7)
	structure.Validate(res, 4, t)
	res = searchInsert([]int{1, 3, 5, 6}, 0)
	structure.Validate(res, 0, t)
}

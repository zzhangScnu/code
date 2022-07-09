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

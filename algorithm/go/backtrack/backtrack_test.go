package backtrack

import (
	. "algorithm.com/structure"
	"fmt"
	"testing"
)

// 候选集无重复，不可重复选
// 全排列
func TestPermute(t *testing.T) {
	res := permute([]int{1, 2, 3})
	Validate(len(res), 6, t)
}

func TestSubsets(t *testing.T) {
	res := subsets([]int{1, 2, 3})
	Validate(len(res), 8, t)
	fmt.Println(res)
}

// 候选集有重复，不可重复选
// 全排列
func TestPermuteUnique(t *testing.T) {
	res := permuteUnique([]int{1, 1, 2})
	fmt.Println(res)
	Validate(len(res), 3, t)
}

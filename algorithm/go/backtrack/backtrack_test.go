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

// 子集
func TestSubsets(t *testing.T) {
	res := subsets([]int{1, 2, 3})
	Validate(len(res), 8, t)
	fmt.Println(res)
}

// 组合
func TestCombine(t *testing.T) {
	res := combine(4, 2)
	Validate(len(res), 6, t)
	fmt.Println(res)
}

// 组合
func TestCombinationSumIII(t *testing.T) {
	res := combinationSum3(3, 9)
	Validate(len(res), 3, t)
	fmt.Println(res)
}

// 候选集有重复，不可重复选
// 全排列
func TestPermuteUnique(t *testing.T) {
	res := permuteUnique([]int{1, 2, 2})
	fmt.Println(res)
	Validate(len(res), 3, t)
}

// 子集
func TestSubsetsWithDup(t *testing.T) {
	res := subsetsWithDup([]int{1, 2, 2})
	Validate(len(res), 6, t)
	fmt.Println(res)
}

// 组合
func TestCombinationSumII(t *testing.T) {
	res := combinationSum2([]int{10, 1, 2, 7, 6, 1, 5}, 8)
	Validate(len(res), 4, t)
	fmt.Println(res)
}

// 候选集无重复，可重复选
func TestCombinationSum(t *testing.T) {
	res := combinationSum([]int{2, 3, 6, 7}, 7)
	Validate(len(res), 2, t)
	fmt.Println(res)
}

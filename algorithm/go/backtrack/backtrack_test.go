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

func TestCombinationSumIV(t *testing.T) {
	res := combinationSum4([]int{4, 2, 1}, 32)
	//Validate(res, 7, t)
	fmt.Println(res)
}

// 其他回溯
func TestLetterCombinations(t *testing.T) {
	res := letterCombinations("23")
	fmt.Println(res)
}

func TestGenerateParenthesis(t *testing.T) {
	res := generateParenthesis(3)
	Validate(len(res), 5, t)
	fmt.Println(res)
}

func TestFindTargetSumWays(t *testing.T) {
	res := findTargetSumWays([]int{1, 1, 1, 1, 1}, 3)
	Validate(res, 5, t)
}

func TestFindTargetSumWaysDistinctly(t *testing.T) {
	res := findTargetSumWaysDistinctly([]int{1, 1, 1, 1, 1}, 3)
	Validate(res, 5, t)
}

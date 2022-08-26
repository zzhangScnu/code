package two_pointers

import (
	"algorithm.com/structure"
	"testing"
)

func TestIsSubSequence(t *testing.T) {
	structure.Validate(isSubsequence("abc", "ahbgdc"), true, t)
	structure.Validate(isSubsequence("axc", "ahbgdc"), false, t)
}

func Test2Sum(t *testing.T) {
	structure.ValidateDeep(twoSum([]int{2, 7, 11, 15}, 9, 0), []int{0, 1}, t)
	structure.ValidateDeep(twoSum([]int{3, 2, 4}, 6, 0), []int{1, 2}, t)
	structure.ValidateDeep(twoSum([]int{3, 3}, 6, 0), []int{0, 1}, t)
}

func Test3Sum(t *testing.T) {
	structure.ValidateDeep(nSum([]int{-1, 0, 1, 2, -1, -4}, 3, 0), [][]int{{-1, -1, 2}, {-1, 0, 1}}, t)
	structure.ValidateDeep(nSum([]int{0, 1, 1}, 3, 0), [][]int{}, t)
	structure.ValidateDeep(nSum([]int{0, 0, 0, 0}, 3, 0), [][]int{{0, 0, 0}}, t)
}

func Test3SumSmaller(t *testing.T) {
	structure.Validate(threeSumSmaller([]int{-2, 0, 1, 3}, 2), 2, t)
}

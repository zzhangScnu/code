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

func TestThreeSumCloset(t *testing.T) {
	structure.Validate(threeSumClosest([]int{-1, 2, 1, -4}, 1), 2, t)
	structure.Validate(threeSumClosest([]int{0, 0, 0}, 1), 0, t)
	structure.Validate(threeSumClosest([]int{1, 1, 1, 1}, 0), 3, t)
}

func TestRemoveDuplicates(t *testing.T) {
	structure.Validate(removeDuplicates([]int{1, 1, 2}), 2, t)
	structure.Validate(removeDuplicates([]int{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}), 5, t)
}

func TestAdvantageCount(t *testing.T) {
	structure.ValidateDeep(advantageCount([]int{2, 7, 11, 15}, []int{1, 10, 4, 11}), []int{2, 11, 7, 15}, t)
	structure.ValidateDeep(advantageCount([]int{12, 24, 8, 32}, []int{13, 25, 32, 11}), []int{24, 32, 8, 12}, t)
	structure.ValidateDeep(advantageCount([]int{2, 0, 4, 1, 2}, []int{1, 3, 0, 0, 2}), []int{2, 0, 2, 1, 4}, t) // 不只有一种解法
}

func TestAdvantageCountBySort(t *testing.T) {
	structure.ValidateDeep(advantageCount([]int{2, 7, 11, 15}, []int{1, 10, 4, 11}), []int{2, 11, 7, 15}, t)
	structure.ValidateDeep(advantageCount([]int{12, 24, 8, 32}, []int{13, 25, 32, 11}), []int{24, 32, 8, 12}, t)
	structure.ValidateDeep(advantageCountBySort([]int{2, 0, 4, 1, 2}, []int{1, 3, 0, 0, 2}), []int{2, 0, 2, 1, 4}, t) // []int{2, 4, 2, 1, 0}也可以
}

func TestTrap(t *testing.T) {
	structure.Validate(trap([]int{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}), 6, t)
	structure.Validate(trap([]int{4, 2, 0, 3, 2, 5}), 9, t)
}

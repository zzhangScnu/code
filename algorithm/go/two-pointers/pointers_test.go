package two_pointers

import (
	. "algorithm.com/structure"
	"fmt"
	"testing"
)

func TestIsSubSequence(t *testing.T) {
	Validate(isSubsequence("abc", "ahbgdc"), true, t)
	Validate(isSubsequence("axc", "ahbgdc"), false, t)
}

func Test2Sum(t *testing.T) {
	ValidateDeep(twoSum([]int{2, 7, 11, 15}, 9, 0), []int{0, 1}, t)
	ValidateDeep(twoSum([]int{3, 2, 4}, 6, 0), []int{1, 2}, t)
	ValidateDeep(twoSum([]int{3, 3}, 6, 0), []int{0, 1}, t)
}

func Test3Sum(t *testing.T) {
	ValidateDeep(nSum([]int{-1, 0, 1, 2, -1, -4}, 3, 0), [][]int{{-1, -1, 2}, {-1, 0, 1}}, t)
	ValidateDeep(nSum([]int{0, 1, 1}, 3, 0), [][]int{}, t)
	ValidateDeep(nSum([]int{0, 0, 0, 0}, 3, 0), [][]int{{0, 0, 0}}, t)
}

func Test3SumSmaller(t *testing.T) {
	Validate(threeSumSmaller([]int{-2, 0, 1, 3}, 2), 2, t)
}

func TestThreeSumCloset(t *testing.T) {
	Validate(threeSumClosest([]int{-1, 2, 1, -4}, 1), 2, t)
	Validate(threeSumClosest([]int{0, 0, 0}, 1), 0, t)
	Validate(threeSumClosest([]int{1, 1, 1, 1}, 0), 3, t)
}

func TestRemoveDuplicates(t *testing.T) {
	Validate(removeDuplicates([]int{1, 1, 2}), 2, t)
	Validate(removeDuplicates([]int{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}), 5, t)
}

func TestAdvantageCount(t *testing.T) {
	ValidateDeep(advantageCount([]int{2, 7, 11, 15}, []int{1, 10, 4, 11}), []int{2, 11, 7, 15}, t)
	ValidateDeep(advantageCount([]int{12, 24, 8, 32}, []int{13, 25, 32, 11}), []int{24, 32, 8, 12}, t)
	ValidateDeep(advantageCount([]int{2, 0, 4, 1, 2}, []int{1, 3, 0, 0, 2}), []int{2, 0, 2, 1, 4}, t) // 不只有一种解法
}

func TestAdvantageCountBySort(t *testing.T) {
	ValidateDeep(advantageCount([]int{2, 7, 11, 15}, []int{1, 10, 4, 11}), []int{2, 11, 7, 15}, t)
	ValidateDeep(advantageCount([]int{12, 24, 8, 32}, []int{13, 25, 32, 11}), []int{24, 32, 8, 12}, t)
	ValidateDeep(advantageCountBySort([]int{2, 0, 4, 1, 2}, []int{1, 3, 0, 0, 2}), []int{2, 0, 2, 1, 4}, t) // []int{2, 4, 2, 1, 0}也可以
}

func TestTrap(t *testing.T) {
	Validate(trap([]int{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}), 6, t)
	Validate(trap([]int{4, 2, 0, 3, 2, 5}), 9, t)
}

func TestMaxArea(t *testing.T) {
	Validate(maxArea([]int{1, 8, 6, 2, 5, 4, 8, 3, 7}), 49, t)
	Validate(maxArea([]int{1, 1}), 1, t)
}

func TestIntervalIntersection(t *testing.T) {
	ValidateDeep(intervalIntersection([][]int{{0, 2}, {5, 10}, {13, 23}, {24, 25}}, [][]int{{1, 5}, {8, 12}, {15, 24}, {25, 26}}), [][]int{{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}}, t)
	ValidateDeep(intervalIntersection([][]int{}, [][]int{{4, 8}, {10, 12}}), [][]int{}, t)
	ValidateDeep(intervalIntersection([][]int{{1, 3}, {5, 9}}, [][]int{}), [][]int{}, t)
	ValidateDeep(intervalIntersection([][]int{{1, 7}}, [][]int{{3, 10}}), [][]int{{3, 7}}, t)
}

func TestMerge(t *testing.T) {
	ValidateDeep(merge([][]int{{1, 3}, {2, 6}, {8, 10}, {15, 18}}), [][]int{{1, 6}, {8, 10}, {15, 18}}, t)
	ValidateDeep(merge([][]int{{1, 4}, {4, 5}}), [][]int{{1, 5}}, t)
	ValidateDeep(merge([][]int{{1, 3}}), [][]int{{1, 3}}, t)
	ValidateDeep(merge([][]int{{1, 4}, {5, 6}}), [][]int{{1, 4}, {5, 6}}, t)
	ValidateDeep(merge([][]int{{1, 4}, {0, 2}, {3, 5}}), [][]int{{0, 5}}, t)
}

func TestIsPalindrome(t *testing.T) {
	Validate(isPalindrome("A man, a plan, a canal: Panama"), true, t)
	Validate(isPalindrome("race a car"), false, t)
	Validate(isPalindrome(".,"), true, t)
	Validate(isPalindrome("0P"), false, t)
}

func TestFindContinuousSequence(t *testing.T) {
	ValidateDeep(findContinuousSequence(9), [][]int{{2, 3, 4}, {4, 5}}, t)
}

func TestDeleteDuplicateII(t *testing.T) {
	param := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5, Next: nil}}}}}}}
	expected := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 5, Next: nil}}}
	fmt.Printf("%+v", expected)
	fmt.Printf("%+v", deleteDuplicatesII(param))
}

func TestLongestPalindrome(t *testing.T) {
	Validate(longestPalindrome("babad"), "bab", t)
}

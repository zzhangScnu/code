package sliding_window

import (
	"algorithm.com/structure"
	"testing"
)

func TestLengthOfLongestSubstring(t *testing.T) {
	structure.Validate(lengthOfLongestSubstring("abcabcbb"), 3, t)
	structure.Validate(lengthOfLongestSubstring("bbbbb"), 1, t)
	structure.Validate(lengthOfLongestSubstring("pwwkew"), 3, t)
	structure.Validate(lengthOfLongestSubstring("aab"), 2, t)
}

func TestMinWindow(t *testing.T) {
	structure.Validate(minWindow("ADOBECODEBANC", "ABC"), "BANC", t)
	structure.Validate(minWindow("a", "a"), "a", t)
	structure.Validate(minWindow("a", "aa"), "", t)
}

func TestFindAnagrams(t *testing.T) {
	structure.ValidateDeep(findAnagrams("cbaebabacd", "abc"), []int{0, 6}, t)
	structure.ValidateDeep(findAnagrams("abab", "ab"), []int{0, 1, 2}, t)
}

func TestCheckInclusion(t *testing.T) {
	structure.Validate(checkInclusion("ab", "eidbaooo"), true, t)
	structure.Validate(checkInclusion("ab", "eidboaoo"), false, t)
}

func TestMaxSlidingWindow(t *testing.T) {
	structure.ValidateDeep(maxSlidingWindow([]int{1, 3, -1, -3, 5, 3, 6, 7}, 3), []int{3, 3, 5, 5, 6, 7}, t)
	structure.ValidateDeep(maxSlidingWindow([]int{1}, 1), []int{1}, t)
}

func TestMinOperations(t *testing.T) {
	structure.Validate(minOperations([]int{1, 1}, 3), -1, t)
	structure.Validate(minOperations([]int{1, 1, 4, 2, 3}, 5), 2, t)
	structure.Validate(minOperations([]int{5, 6, 7, 8, 9}, 4), -1, t)
	structure.Validate(minOperations([]int{3, 2, 20, 1, 1, 3}, 10), 5, t)
}

func TestContainsNearbyDuplicate(t *testing.T) {
	structure.Validate(containsNearbyDuplicate([]int{1, 2, 3, 1}, 3), true, t)
	structure.Validate(containsNearbyDuplicate([]int{1, 0, 1, 1}, 1), true, t)
	structure.Validate(containsNearbyDuplicate([]int{1, 2, 3, 1, 2, 3}, 2), false, t)
	structure.Validate(containsNearbyDuplicate([]int{0, 1, 2, 3, 2, 5}, 3), true, t)
	structure.Validate(containsNearbyDuplicate([]int{1, 2, 1}, 0), false, t)

}

func TestContainsNearbyDuplicateMap(t *testing.T) {
	structure.Validate(containsNearbyDuplicateMap([]int{1, 2, 3, 1}, 3), true, t)
	structure.Validate(containsNearbyDuplicateMap([]int{1, 0, 1, 1}, 1), true, t)
	structure.Validate(containsNearbyDuplicateMap([]int{1, 2, 3, 1, 2, 3}, 2), false, t)
	structure.Validate(containsNearbyDuplicateMap([]int{0, 1, 2, 3, 2, 5}, 3), true, t)
	structure.Validate(containsNearbyDuplicateMap([]int{1, 2, 1}, 0), false, t)
}

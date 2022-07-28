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

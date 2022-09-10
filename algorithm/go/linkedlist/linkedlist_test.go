package main

import (
	. "algorithm.com/structure"
	"testing"
)

func TestRemoveNthNodeFromEndOfList(t *testing.T) {
	param := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5, Next: nil}}}}}
	expectVal := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 5, Next: nil}}}}
	ValidateDeep(removeNthNodeFromEndOfList(param, 2), expectVal, t)
}

package main

import (
	. "algorithm.com/structure"
	"fmt"
	"testing"
)

func TestRemoveNthNodeFromEndOfList(t *testing.T) {
	param := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5, Next: nil}}}}}
	expectVal := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 5, Next: nil}}}}
	ValidateDeep(removeNthNodeFromEndOfList(param, 2), expectVal, t)
}

func TestMergeKSortedLists(t *testing.T) {
	l1 := &ListNode{Val: 1, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5, Next: nil}}}
	l2 := &ListNode{Val: 1, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: nil}}}
	l3 := &ListNode{Val: 2, Next: &ListNode{Val: 6, Next: nil}}
	param := []*ListNode{l1, l2, l3}
	expectVal := &ListNode{Val: 1, Next: &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5, Next: &ListNode{Val: 6, Next: nil}}}}}}}}
	fmt.Printf("expect: %v", expectVal)
	fmt.Printf("actual: %v", mergeKSortedLists(param))
}

func TestReverseList(t *testing.T) {
	param := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5, Next: nil}}}}}
	res := reverseIterating(param)
	fmt.Printf("%v", res)
}

func TestReverseBetween(t *testing.T) {
	param := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: nil}}}
	res := reverseBetween(param, 1, 2)
	res2 := reverseBetween(param, 2, 3)
	fmt.Printf("%+v", res)
	fmt.Printf("%+v", res2)
}

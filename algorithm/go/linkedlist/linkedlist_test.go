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
	res := reverseIterating1(param)
	fmt.Printf("%v", res)
}

func TestReverseBetween(t *testing.T) {
	param := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: nil}}}
	res := reverseBetween(param, 1, 2)
	res2 := reverseBetween(param, 2, 3)
	fmt.Printf("%+v", res)
	fmt.Printf("%+v", res2)
}

func TestReverseKGroup(t *testing.T) {
	param := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5, Next: nil}}}}}
	res := reverseKGroup(param, 2)
	fmt.Printf("%+v", res)
}

func TestPrintLinkedListReversely(t *testing.T) {
	param := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5, Next: nil}}}}}
	traverseByPrint(param)
}

func traverseByPrint(p *ListNode) {
	if p == nil {
		return
	}
	traverseByPrint(p.Next)
	fmt.Print(p.Val)
}

func TestIsPalindrome(t *testing.T) {
	param := &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 2, Next: &ListNode{Val: 1, Next: nil}}}}
	Validate(isPalindrome2(param), true, t)
}

func TestMergeTwoLists(t *testing.T) {
	list1 := &ListNode{Val: -9, Next: &ListNode{Val: 3, Next: nil}}
	list2 := &ListNode{Val: 5, Next: &ListNode{Val: 7, Next: nil}}
	res := mergeTwoLists(list1, list2)
	fmt.Println(res)
}

package main

import . "algorithm.com/structure"

// [1,2,3,4,5], n=2
// n从1开始
func removeNthNodeFromEndOfList(l *ListNode, n int) *ListNode {
	dummyHead := ListNode{
		Val:  -1,
		Next: l,
	}
	slow := dummyHead.Next
	step, fast := 0, slow
	for fast != nil && step < n {
		fast = fast.Next
		step++
	}
	for fast.Next != nil {
		fast = fast.Next
		slow = slow.Next
	}
	slow.Next = slow.Next.Next
	return dummyHead.Next
}

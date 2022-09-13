package main

import . "algorithm.com/structure"

//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,2,1]
//输出：true
//
//
// 示例 2：
//
//
//输入：head = [1,2]
//输出：false
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围[1, 10⁵] 内
// 0 <= Node.val <= 9
//
//
//
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
// Related Topics 栈 递归 链表 双指针

var l *ListNode

func isPalindrome(head *ListNode) bool {
	l = head
	return isPalindromeTraverse(l)
}

// 遍历整条链表，l一次，p两次
// 需要额外O(n)的栈空间
func isPalindromeTraverse(p *ListNode) bool {
	if p == nil {
		return true
	}
	res := isPalindromeTraverse(p.Next)
	res = res && l.Val == p.Val
	l = l.Next
	return res
}

func isPalindrome2(head *ListNode) bool {
	pivot := findPivot(head)
	l := head
	r := reverseIterating(pivot)
	for r != nil {
		if l.Val != r.Val {
			return false
		}
		l = l.Next
		r = r.Next
	}
	return true
}

func findPivot(head *ListNode) *ListNode {
	slow, fast := head, head
	for fast != nil && fast.Next != nil {
		fast = fast.Next.Next
		slow = slow.Next
	}
	// fast指向链表末尾时，代表链表有奇数个节点，slow需要再往前一步才是指向中间
	if fast != nil {
		slow = slow.Next
	}
	return slow
}

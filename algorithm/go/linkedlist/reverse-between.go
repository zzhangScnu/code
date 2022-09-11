package main

import . "algorithm.com/structure"

//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
//
//
// 示例 2：
//
//
//输入：head = [5], left = 1, right = 1
//输出：[5]
//
//
//
//
// 提示：
//
//
// 链表中节点数目为 n
// 1 <= n <= 500
// -500 <= Node.val <= 500
// 1 <= left <= right <= n
//
//
//
//
// 进阶： 你可以使用一趟扫描完成反转吗？
// Related Topics 链表

func reverseBetween(head *ListNode, left int, right int) *ListNode {
	dummy := &ListNode{Next: head}
	pre, r := findNodes(dummy, left-1, right)
	l, nxt := pre.Next, r.Next
	reversedHead := reverseBetweenNode(l, r)
	// 衔接右半部分
	pre.Next.Next = nxt
	// 衔接左半部分
	pre.Next = reversedHead
	if nxt == nil {
		return reversedHead
	}
	return dummy.Next
}

func findNodes(head *ListNode, left int, right int) (*ListNode, *ListNode) {
	step := 0
	p := head
	var l, r *ListNode
	for step < right {
		if step == left {
			l = p
		}
	}
	r = p
	return l, r
}

// 前闭后闭，返回反转后的头节点
func reverseBetweenNode(left *ListNode, right *ListNode) *ListNode {
	if left == right {
		return left
	}
	newHead := reverseBetweenNode(left.Next, right)
	left.Next.Next = left
	return newHead
}

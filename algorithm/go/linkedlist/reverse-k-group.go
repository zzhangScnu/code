package main

import . "algorithm.com/structure"

//给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
//
//
// 示例 2：
//
//
//
//
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
//
//
//
//提示：
//
//
// 链表中的节点数目为 n
// 1 <= k <= n <= 5000
// 0 <= Node.val <= 1000
//
//
//
//
// 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
//
//
//
// Related Topics 递归 链表

func reverseKGroup(head *ListNode, k int) *ListNode {
	l, r := head, head
	for i := 0; i < k; i++ {
		if r == nil {
			return head
		}
		r = r.Next
	}
	newHead := reverse(l, r)
	l.Next = reverseKGroup(r, k)
	return newHead
}

// 前闭后开
// 递归+递归会很慢，这里可以考虑用迭代实现
func reverse(left *ListNode, right *ListNode) *ListNode {
	if left.Next == right {
		return left
	}
	newHead := reverse(left.Next, right)
	left.Next.Next = left
	left.Next = nil
	return newHead
}

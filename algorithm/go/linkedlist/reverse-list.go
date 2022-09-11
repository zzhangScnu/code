package main

import . "algorithm.com/structure"

//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
//
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
//
//
// 示例 2：
//
//
//输入：head = [1,2]
//输出：[2,1]
//
//
// 示例 3：
//
//
//输入：head = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目范围是 [0, 5000]
// -5000 <= Node.val <= 5000
//
//
//
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
//
//
// Related Topics 递归 链表

func reverseList(head *ListNode) *ListNode {
	return reverseRecursively(head)
}

// 定义：递归地翻转链表，返回处理后的头节点
func reverseRecursively(head *ListNode) *ListNode {
	if head == nil || head.Next == nil { // 防止下面[head.Next.Next = head]panic
		return head
	}
	newHead := reverseRecursively(head.Next)
	head.Next.Next = head
	head.Next = nil
	return newHead
}

//func reverseIterating(head *ListNode) *ListNode {
//	if head == nil {
//		return nil
//	}
//	var pre *ListNode
//	cur := head
//	for cur != nil { // 这里要以nxt做判断，否则这样写，到最后cur会指向nil(nxt)
//		nxt := cur.Next
//		cur.Next = pre
//		pre = cur
//		cur = nxt
//	}
//	return cur
//}

func reverseIterating(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	var pre *ListNode
	cur, nxt := head, head.Next
	for nxt != nil {
		cur.Next = pre
		pre = cur
		cur = nxt
		nxt = nxt.Next
	}
	cur.Next = pre
	return cur
}

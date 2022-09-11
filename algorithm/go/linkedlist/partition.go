package main

import . "algorithm.com/structure"

//给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
//
// 你应当 保留 两个分区中每个节点的初始相对位置。
//
//
//
// 示例 1：
//
//
//输入：head = [1,4,3,2,5,2], x = 3
//输出：[1,2,2,4,3,5]
//
//
// 示例 2：
//
//
//输入：head = [2,1], x = 2
//输出：[1,2]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 200] 内
// -100 <= Node.val <= 100
// -200 <= x <= 200
//
// Related Topics 链表 双指针

func partition(head *ListNode, x int) *ListNode {
	dummyHeadS := &ListNode{
		Val:  -1,
		Next: nil,
	}
	dummyHeadL := &ListNode{
		Val:  -1,
		Next: nil,
	}
	s, l, p := dummyHeadS, dummyHeadL, head
	for p != nil {
		if p.Val < x {
			s.Next = p
			s = s.Next
		} else {
			l.Next = p
			l = l.Next
		}
		// 需要断开p点跟原链表之间的关联，否则可能会出现s链表中的节点指向l中的节点的情况
		tmp := p.Next
		p.Next = nil
		p = tmp
	}
	s.Next = dummyHeadL.Next
	return dummyHeadS.Next
}

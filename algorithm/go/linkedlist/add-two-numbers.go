//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//
//
// 示例 1：
//
//
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
//
//
// 示例 2：
//
//
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
//
// 示例 3：
//
//
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
//
//
//
//
// 提示：
//
//
// 每个链表中的节点数在范围 [1, 100] 内
// 0 <= Node.val <= 9
// 题目数据保证列表表示的数字不含前导零
//
// Related Topics 递归 链表 数学

package main

import (
	. "algorithm.com/structure"
	"fmt"
)

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	carry := 0
	resultHead := &ListNode{}
	h, p, q := resultHead, l1, l2
	for p != nil && q != nil {
		sum := p.Val + q.Val + carry
		val := sum % 10
		carry = sum / 10
		h.Next = &ListNode{Val: val}
		h = h.Next
		p = p.Next
		q = q.Next
	}
	for p != nil {
		sum := p.Val + carry
		val := sum % 10
		carry = sum / 10
		h.Next = &ListNode{Val: val}
		h = h.Next
		p = p.Next
	}
	for q != nil {
		sum := q.Val + carry
		val := sum % 10
		carry = sum / 10
		h.Next = &ListNode{Val: val}
		h = h.Next
		q = q.Next
	}
	if carry != 0 {
		h.Next = &ListNode{Val: carry}
	}
	return resultHead.Next
}

func main() {
	l1 := &ListNode{Val: 2, Next: &ListNode{Val: 4, Next: &ListNode{Val: 3, Next: nil}}}
	l2 := &ListNode{Val: 5, Next: &ListNode{Val: 6, Next: &ListNode{Val: 4, Next: nil}}}
	result := addTwoNumbers(l1, l2)
	l1 = &ListNode{Val: 9, Next: nil}
	l2 = &ListNode{Val: 9, Next: nil}
	result = addTwoNumbers(l1, l2)
	l1 = &ListNode{Val: 9, Next: &ListNode{Val: 9, Next: &ListNode{Val: 9, Next: &ListNode{Val: 9, Next: &ListNode{Val: 9, Next: &ListNode{Val: 9, Next: &ListNode{Val: 9, Next: nil}}}}}}}
	l2 = &ListNode{Val: 9, Next: &ListNode{Val: 9, Next: &ListNode{Val: 9, Next: &ListNode{Val: 9, Next: nil}}}}
	result = addTwoNumbers(l1, l2)
	fmt.Println(*result)
}

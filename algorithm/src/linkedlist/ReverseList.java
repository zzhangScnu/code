package linkedlist;//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
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

import structure.ListNode;

import java.util.Objects;

/**
 * @author lihua
 * @since 2021/10/21
 */
public class ReverseList {

	public ListNode reverseList(ListNode head) {
		if (Objects.isNull(head)) {
			return null;
		}
		if (Objects.isNull(head.next)) {
			return head;
		}
		ListNode newHead = reverseList(head.next);
		// 递归处理完的长度为n-1的子链表，跟头节点需要重新连接一下
		head.next.next = head;
		head.next = null;
		return newHead;
	}

	public ListNode reverseListInIteration(ListNode head) {
		if (Objects.isNull(head)) {
			return null;
		}
		ListNode pre = null;
		ListNode cur = head;
		// 这个nxt其实是起到中间结点的作用，它保存了cur指向pre之前指向的下一个结点
		ListNode nxt = head.next;
		while (Objects.nonNull(nxt)) {
			cur.next = pre;
			pre = cur;
			cur = nxt;
			nxt = nxt.next;
		}
		// 最后nxt为null的时候，表示cur指向最后一个节点。这时候需要指向之前反转过的子链表，完成整个反转过程
		cur.next = pre;
		return cur;
	}
}

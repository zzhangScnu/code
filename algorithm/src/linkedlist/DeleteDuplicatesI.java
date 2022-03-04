package linkedlist;

// 83-Remove Duplicates from Sorted List
// remove-duplicates-from-sorted-list
//给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
//
//
//
// 示例 1：
//
//
//输入：head = [1,1,2]
//输出：[1,2]
//
//
// 示例 2：
//
//
//输入：head = [1,1,2,3,3]
//输出：[1,2,3]
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围 [0, 300] 内
// -100 <= Node.val <= 100
// 题目数据保证链表已经按升序 排列
//
// Related Topics 链表

import structure.ListNode;

/**
 * @author lihua
 * @since 2022/3/4
 */
public class DeleteDuplicatesI {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-101, head);
        ListNode pre = dummy;
        ListNode nxt = dummy.next;
        while (nxt != null) {
            if (nxt.next != null && nxt.val == nxt.next.val) {
                while (nxt.next != null && nxt.val == nxt.next.val) {
                    nxt = nxt.next;
                }
                pre.next = nxt;
            } else {
                pre = pre.next;
                nxt = nxt.next;
            }
        }
        return dummy.next;
    }
}

package linkedlist;

// 82-Remove Duplicates from Sorted List II
// remove-duplicates-from-sorted-list-ii
//给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
//
//
// 示例 2：
//
//
//输入：head = [1,1,1,2,3]
//输出：[2,3]
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
// Related Topics 链表 双指针

import structure.ListNode;

/**
 * @author lihua
 * @since 2022/3/4
 */
@SuppressWarnings("DuplicatedCode")
public class DeleteDuplicatesII {

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
                // 跟1唯一的不同在于，找到重复的子链表后，进行指针改变时，nxt再往后挪一个位置
                // 意味着重复的全都被覆盖了，都会被去掉
                nxt = nxt.next;
                pre.next = nxt;
            } else {
                pre = pre.next;
                nxt = nxt.next;
            }
        }
        return dummy.next;
    }
}

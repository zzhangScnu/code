package linkedlist;

// 21-合并两个有序链表
// merge-two-sorted-lists
//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
//
//
// 示例 1：
//
//
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
//
//
// 示例 2：
//
//
//输入：l1 = [], l2 = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：l1 = [], l2 = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 两个链表的节点数目范围是 [0, 50]
// -100 <= Node.val <= 100
// l1 和 l2 均按 非递减顺序 排列
//
// Related Topics 递归 链表

import structure.ListNode;

/**
 * @author lihua
 * @since 2021/12/2
 */
public class MergeTwoLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode p = list1;
        ListNode q = list2;
        ListNode newList = new ListNode(-101, null);
        ListNode head = newList;
        while (p != null && q != null) {
            if (p.val <= q.val) {
                newList.next = p;
                p = p.next;
            } else {
                newList.next = q;
                q = q.next;
            }
            newList = newList.next;
        }
        // 直接指向剩下的链
        if (p == null) {
            newList.next = q;
        }
        if (q == null) {
            newList.next = p;
        }
        return head.next;
    }

    public static void main(String[] args) {
        MergeTwoLists clazz = new MergeTwoLists();
        ListNode list1 = new ListNode(-2, new ListNode(5, null));
        ListNode list2 = new ListNode(-9, new ListNode(-6, new ListNode(-3, new ListNode(-1, new ListNode(1, new ListNode(6, null))))));
        ListNode result = clazz.mergeTwoLists(list1, list2);
        assert result != null;
    }
}

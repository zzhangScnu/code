package linkedlist;

import structure.ListNode;

// 148-排序链表
// sort-list
//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//
// 进阶：
//
//
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
//
//
//
//
// 示例 1：
//
//
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
//
//
// 示例 2：
//
//
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
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
// 链表中节点的数目在范围 [0, 5 * 104] 内
// -105 <= Node.val <= 105
//
// Related Topics 链表 双指针 分治 排序 归并排序

/**
 * @author lihua
 * @since 2021/12/4
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        // tail指向的是尾结点的后一个结点，即null
        return sortList(head, null);
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            // 切分成单个结点，返回上层合并
            head.next = null;
            return head;
        }
        ListNode fast = head;
        ListNode mid = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            mid = mid.next;
        }
        // 前闭后开区间
        ListNode p = sortList(head, mid);
        ListNode q = sortList(mid, tail);
        return merge(p, q);
    }

    /**
     * p和q都不会是空结点
     */
    private ListNode merge(ListNode p, ListNode q) {
        ListNode newHead = new ListNode(-1, null);
        ListNode cur = newHead;
        while (p != null && q != null) {
            if (p.val <= q.val) {
                cur.next = p;
                p = p.next;
            } else {
                cur.next = q;
                q = q.next;
            }
            cur = cur.next;
        }
        if (p == null) {
            cur.next = q;
        }
        if (q == null) {
            cur.next = p;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        SortList clazz = new SortList();
        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3, null))));
        ListNode result = clazz.sortList(head);
        assert result != null;
    }
}

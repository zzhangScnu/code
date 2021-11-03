package linkedlist;//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
//
// 进阶：
//
//
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
//
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
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
//
//
// 示例 3：
//
//
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
//
//
// 示例 4：
//
//
//输入：head = [1], k = 1
//输出：[1]
//
//
//
//
//
// 提示：
//
//
// 列表中节点的数量在范围 sz 内
// 1 <= sz <= 5000
// 0 <= Node.val <= 1000
// 1 <= k <= sz
//
// Related Topics 递归 链表

import structure.ListNode;

import java.util.Objects;

/**
 * @author lihua
 * @since 2021/10/23
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (Objects.isNull(head)) {
            return null;
        }
        ListNode p = head;
        ListNode q = head;
        for (int i = 0; i < k; i++) {
            // 不足k个，就不反转了
            if (Objects.isNull(q)) {
                return head;
            }
            q = q.next;
        }
        ListNode newHead = reverseBetween(p, q);
        p.next = reverseKGroup(q, k);
        return newHead;
    }

    /**
     * 反转[p, q)之间的节点，左闭右开的
     */
    private ListNode reverseBetween(ListNode p, ListNode q) {
        // 左闭右开，反转后的头节点是右区间的上一个节点
        // 如果用equals判定的话，当k=1，p指向最后一个节点的时候，会报空指针错误
        if (p.next == q) {
            return p;
        }
        ListNode last = reverseBetween(p.next, q);
        p.next.next = p;
        p.next = q;
        return last;
    }
}

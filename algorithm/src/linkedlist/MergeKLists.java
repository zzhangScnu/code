package linkedlist;

import structure.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

// 23-合并K个升序链表
// merge-k-sorted-lists
//给你一个链表数组，每个链表都已经按升序排列。
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。
//
//
//
// 示例 1：
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
//
//
// 示例 2：
//
// 输入：lists = []
//输出：[]
//
//
// 示例 3：
//
// 输入：lists = [[]]
//输出：[]
//
//
//
//
// 提示：
//
//
// k == lists.length
// 0 <= k <= 10^4
// 0 <= lists[i].length <= 500
// -10^4 <= lists[i][j] <= 10^4
// lists[i] 按 升序 排列
// lists[i].length 的总和不超过 10^4
//
// Related Topics 链表 分治 堆（优先队列） 归并排序

/**
 * @author lihua
 * @since 2021/12/2
 */
public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        // 头结点加入堆中
        for (ListNode p : lists) {
            if (p != null) {
                queue.add(p);
            }
        }
        ListNode head = new ListNode(-1, null);
        ListNode p = head;
        ListNode cur;
        while (!queue.isEmpty()) {
            cur = queue.remove();
            if (cur.next != null) {
                queue.add(cur.next);
            }
            p.next = cur;
            p = p.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        MergeKLists clazz = new MergeKLists();
        ListNode list1 = new ListNode(-2, new ListNode(-1, new ListNode(-1, new ListNode(-1, null))));
        ListNode list2 = null;
        ListNode result = clazz.mergeKLists(new ListNode[]{list1, list2});
        assert result != null;
    }
}

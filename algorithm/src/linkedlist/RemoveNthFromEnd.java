package linkedlist;

import structure.ListNode;
// 19-删除链表的倒数第 N 个结点
// remove-nth-node-from-end-of-list
//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 进阶：你能尝试使用一趟扫描实现吗？
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
//
//
// 示例 2：
//
//
//输入：head = [1], n = 1
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1,2], n = 1
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中结点的数目为 sz
// 1 <= sz <= 30
// 0 <= Node.val <= 100
// 1 <= n <= sz
//
// Related Topics 链表 双指针

/**
 * 假设链表长度为l
 * 倒数第k个结点，即正数第l-k+1个结点
 * 当fast走了k步，还需走l-k+1到尾结点的下一个结点
 * 而如果slow此时在头结点开始走，当fast在尾结点的下一个结点的时候，slow刚好指向倒数第k个结点
 *
 * @author lihua
 * @since 2021/11/3
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 增加虚拟头结点。因为slow指向的是要删除结点的前置结点
        // 这样如果要删除头结点的话就不需要特殊判断了
        ListNode dummyHead = new ListNode(0, head);
        ListNode slow = dummyHead;
        ListNode fast = head;
        int k = 0;
        while (k < n) {
            fast = fast.next;
            k++;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // slow最终停下的就是倒数第k-1个结点
        slow.next = slow.next.next;
        // 注意这里是虚拟头结点的下一个结点，也就是真正的头结点
        return dummyHead.next;
    }
}
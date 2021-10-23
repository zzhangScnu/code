//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
//
//
// 示例 2：
//
//
//输入：head = [5], left = 1, right = 1
//输出：[5]
//
//
//
//
// 提示：
//
//

// 链表中节点数目为 n
// 1 <= n <= 500
// -500 <= Node.val <= 500
// 1 <= left <= right <= n
//
//
//
//
// 进阶： 你可以使用一趟扫描完成反转吗？
// Related Topics 链表

import structure.ListNode;

/**
 * @author lihua
 * @since 2021/10/23
 */
public class ReverseBetween {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            // 等价于反转从head开始的m个节点
            return reverseN(head, right);
        }
        // 当将head.next的索引值视为1时，反转区间的起始点从n变为n-1
        // 这里是right - 1的原因： 这样才能保证区间大小为n - m + 1，跟题目中的[n..m]个数一样
        // head后面的子链表被反转后，head.next连接上
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    /**
     * 要是定义在方法体里面，就要层层传递了
     */
    private ListNode successor = null;

    /**
     * 反转从节点p开始的n个节点，即区间为[1..n]的节点
     *
     * @return 反转后的头节点
     */
    private ListNode reverseN(ListNode p, int n) {
        if (n == 1) {
            // 此时p是即将反转的链表的最后一个节点，在反转它自己的同时，记录后驱节点
            successor = p.next;
            return p;
        }
        ListNode newHead = reverseN(p.next, n - 1);
        p.next.next = p;
        p.next = successor;
        return newHead;
    }
}

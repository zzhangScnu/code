package linkedlist;

import structure.ListNode;
// 707-设计链表
//
//设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针
///引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
//
// 在链表类中实现这些功能：
//
//
// get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
// addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
// addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
// addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val 的节点。如果 index 等于链表的长度，则该节点将附加
//到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
// deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
//
//
//
//
// 示例：
//
// MyLinkedList linkedList = new MyLinkedList();
//linkedList.addAtHead(1);
//linkedList.addAtTail(3);
//linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
//linkedList.get(1);            //返回2
//linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//linkedList.get(1);            //返回3
//
//
//
//
// 提示：
//
//
// 所有val值都在 [1, 1000] 之内。
// 操作次数将在 [1, 1000] 之内。
// 请不要使用内置的 LinkedList 库。
//

/**
 * 单链表的实现
 * 特别要注意尾指针的更新
 * 多维护一个size变量，可以方便地判断越界问题
 *
 * @author lihua
 * @since 2022/2/5
 */
@SuppressWarnings("DuplicatedCode")
public class LinkedList {

    /**
     * 虚拟头结点
     */
    private final ListNode head;

    private ListNode tail;

    private int size;

    public LinkedList() {
        this.head = new ListNode(-1);
        this.tail = this.head;
        this.size = 0;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = this.head.next;
        if (newNode.next == null) {
            this.tail = newNode;
        }
        this.head.next = newNode;
        this.size++;
    }

    public void addAtTail(int val) {
        this.tail.next = new ListNode(val);
        this.tail = this.tail.next;
        this.size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index < 0) {
            addAtHead(val);
            return;
        }
        ListNode cur = this.head;
        // 为了定位到目标结点的前一个结点
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = cur.next;
        if (cur.next == null) {
            this.tail = newNode;
        }
        cur.next = newNode;
        this.size++;
    }

    public void delete(int val) {
        ListNode cur = this.head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                if (cur.next == null) {
                    this.tail = cur;
                }
                break;
            }
            cur = cur.next;
        }
        this.size--;
    }

    public void deleteAtIndex(int index) {
        if (index >= size) {
            return;
        }
        ListNode cur = this.head;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        cur.next = cur.next.next;
        if (cur.next == null) {
            this.tail = cur;
        }
        this.size--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this.head;
        while (cur.next != null) {
            sb.append(cur.next.val).append(", ");
            cur = cur.next;
        }
        return sb.toString();
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = this.head;
        while (index >= 0) {
            cur = cur.next;
            index--;
        }
        return cur.val;
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addAtHead(7);
        linkedList.addAtHead(2);
        linkedList.addAtHead(1);
        linkedList.addAtIndex(3, 0);
        linkedList.deleteAtIndex(2);
        linkedList.addAtHead(6);
        linkedList.addAtTail(4);
        int i = linkedList.get(4);
        assert i == 4;
        linkedList.addAtHead(4);
        linkedList.addAtIndex(5, 0);
        linkedList.addAtHead(6);
        System.out.println(linkedList);
    }
}

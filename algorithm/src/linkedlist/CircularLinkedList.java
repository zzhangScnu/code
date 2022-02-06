package linkedlist;

import structure.ListNode;

/**
 * 循环链表的实现
 * 不同之处在于：判断是链表尾结点时，next指向的是头结点而不再是null
 * 多维护一个size，判断越界会方便很多
 *
 * @author lihua
 * @since 2022/2/5
 */
@SuppressWarnings("DuplicatedCode")
public class CircularLinkedList {

    private final ListNode head;

    private ListNode tail;

    private int size;

    public CircularLinkedList() {
        this.head = new ListNode(-1);
        this.head.next = this.head;
        this.tail = this.head;
        this.size = 0;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = this.head.next;
        this.head.next = newNode;
        if (newNode.next == this.head) {
            this.tail = newNode;
        }
        this.size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = this.tail.next;
        this.tail.next = newNode;
        this.tail = newNode;
        this.size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index > size) {
            return;
        }
        ListNode cur = this.head;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = cur.next;
        cur.next = newNode;
        if (newNode.next == this.head) {
            this.tail = newNode;
        }
        this.size++;
    }

    public void delete(int val) {
        ListNode cur = this.head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                if (cur.next == this.head) {
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
        if (cur.next == this.head) {
            this.tail = cur;
        }
        this.size--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this.head.next;
        // 避免死循环
        while (cur != this.head) {
            sb.append(cur.val).append(", ");
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
        CircularLinkedList linkedList = new CircularLinkedList();
        linkedList.addAtHead(3);
        linkedList.addAtHead(2);
        linkedList.addAtHead(1);
        linkedList.addAtTail(4);
        // 1, 2, 0, 3, 4
        linkedList.addAtIndex(2, 0);
        linkedList.delete(1);
        linkedList.deleteAtIndex(0);
        System.out.println(linkedList);
    }
}

package linkedlist;

import structure.ListNode;

/**
 * 循环链表的实现
 * 不同之处在于：判断是链表尾结点时，next指向的是头结点而不再是null
 *
 * @author lihua
 * @since 2022/2/5
 */
@SuppressWarnings("DuplicatedCode")
public class CircularLinkedList {

    private final ListNode head;

    private ListNode tail;

    public CircularLinkedList() {
        this.head = new ListNode(-1);
        this.head.next = this.head;
        this.tail = this.head;
    }

    public void addFirst(int data) {
        ListNode newNode = new ListNode(data);
        newNode.next = this.head.next;
        this.head.next = newNode;
        if (newNode.next == this.head) {
            this.tail = newNode;
        }
    }

    public void addLast(int data) {
        ListNode newNode = new ListNode(data);
        newNode.next = this.tail.next;
        this.tail.next = newNode;
        this.tail = newNode;
    }

    public void addAt(int index, int data) {
        ListNode cur = this.head;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        ListNode newNode = new ListNode(data);
        newNode.next = cur.next;
        cur.next = newNode;
        if (newNode.next == this.head) {
            this.tail = newNode;
        }
    }

    public void delete(int data) {
        ListNode cur = this.head;
        while (cur.next != null) {
            if (cur.next.val == data) {
                cur.next = cur.next.next;
                if (cur.next == this.head) {
                    this.tail = cur;
                }
                break;
            }
            cur = cur.next;
        }
    }

    public void deleteAt(int index) {
        ListNode cur = this.head;
        while (index > 0) {
            cur = cur.next;
            if (cur == this.head) {
                throw new IllegalStateException("index out of bound");
            }
            index--;
        }
        cur.next = cur.next.next;
        if (cur.next == this.head) {
            this.tail = cur;
        }
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

    public static void main(String[] args) {
        CircularLinkedList linkedList = new CircularLinkedList();
        linkedList.addFirst(3);
        linkedList.addFirst(2);
        linkedList.addFirst(1);
        linkedList.addLast(4);
        // 1, 2, 0, 3, 4
        linkedList.addAt(2, 0);
        linkedList.delete(1);
        linkedList.deleteAt(0);
        System.out.println(linkedList);
    }
}

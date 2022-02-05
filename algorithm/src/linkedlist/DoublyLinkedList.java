package linkedlist;

/**
 * @author lihua
 * @since 2022/2/5
 */
@SuppressWarnings("DuplicatedCode")
public class DoublyLinkedList {

    public static class ListNode {

        private final int val;

        private ListNode next;

        private ListNode pre;

        public ListNode(int val) {
            this.val = val;
        }
    }

    private final ListNode head;

    private ListNode tail;

    public DoublyLinkedList() {
        this.head = new ListNode(-1);
        this.tail = this.head;
    }

    public void addFirst(int data) {
        ListNode newNode = new ListNode(data);
        newNode.next = this.head.next;
        // 注意点：这个地方第一次忘记连上了
        if (this.head.next != null) {
            this.head.next.pre = newNode;
        }
        newNode.pre = this.head;
        this.head.next = newNode;
        if (newNode.next == null) {
            this.tail = newNode;
        }
    }

    public void addLast(int data) {
        ListNode newNode = new ListNode(data);
        newNode.pre = this.tail;
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
        // 注意点：这个地方第一次忘记连上了
        if (cur.next != null) {
            cur.next.pre = newNode;
        }
        newNode.pre = cur;
        cur.next = newNode;
        if (newNode.next == null) {
            this.tail = newNode;
        }
    }

    public void delete(int data) {
        ListNode cur = this.head;
        while (cur.next != null) {
            if (cur.next.val == data) {
                ListNode nNext = cur.next.next;
                if (nNext != null) {
                    nNext.pre = cur;
                }
                cur.next = nNext;
                if (cur.next == null) {
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
            if (cur == null) {
                throw new IllegalStateException("index out of bound");
            }
            index--;
        }
        ListNode nNext = cur.next.next;
        if (nNext != null) {
            nNext.pre = cur;
        }
        cur.next = nNext;
        if (cur.next == null) {
            this.tail = cur;
        }
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

    public static void main(String[] args) {
        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.addFirst(3);
        linkedList.addFirst(2);
        linkedList.addFirst(1);
        linkedList.addLast(4);
        // 1, 2, 0, 3, 4
        linkedList.addAt(2, 0);
        linkedList.delete(1);
        linkedList.deleteAt(0);
        linkedList.deleteAt(2);
        System.out.println(linkedList);
    }
}

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
    
    private int size;

    public DoublyLinkedList() {
        this.head = new ListNode(-1);
        this.tail = this.head;
        this.size = 0;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
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
        this.size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        newNode.pre = this.tail;
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
        doAddAtIndex(index, val);
    }

    private void doAddAtIndex(int index, int val) {
        ListNode cur = this.head;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        ListNode newNode = new ListNode(val);
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
        this.size++;
    }

    public void delete(int val) {
        ListNode cur = this.head;
        while (cur.next != null) {
            if (cur.next.val == val) {
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
        ListNode nNext = cur.next.next;
        if (nNext != null) {
            nNext.pre = cur;
        }
        cur.next = nNext;
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
        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.addAtHead(3);
        linkedList.addAtHead(2);
        linkedList.addAtHead(1);
        linkedList.addAtTail(4);
        // 1, 2, 0, 3, 4
        linkedList.addAtIndex(2, 0);
        linkedList.delete(1);
        linkedList.deleteAtIndex(0);
        linkedList.deleteAtIndex(2);
        System.out.println(linkedList);
    }
}

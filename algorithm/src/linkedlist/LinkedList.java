package linkedlist;

/**
 * 单链表的实现
 * 特别要注意尾指针的更新
 *
 * @author lihua
 * @since 2022/2/5
 */
public class LinkedList {

    public static class ListNode {

        private final int data;

        private ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    /**
     * 虚拟头结点
     */
    private final ListNode head;

    private ListNode last;

    public LinkedList() {
        this.head = new ListNode(-1);
        this.last = this.head;
    }

    public void addFirst(int data) {
        ListNode newNode = new ListNode(data);
        newNode.next = this.head.next;
        if (newNode.next == null) {
            this.last = newNode;
        }
        this.head.next = newNode;
    }

    public void addLast(int data) {
        this.last.next = new ListNode(data);
        this.last = this.last.next;
    }

    public void addAt(int index, int data) {
        ListNode cur = this.head;
        // 为了定位到目标结点的前一个结点
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        ListNode newNode = new ListNode(data);
        newNode.next = cur.next;
        if (cur.next == null) {
            this.last = newNode;
        }
        cur.next = newNode;
    }

    public void delete(int data) {
        ListNode cur = this.head;
        while (cur.next != null) {
            if (cur.next.data == data) {
                cur.next = cur.next.next;
                if (cur.next == null) {
                    this.last = cur;
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
            index--;
        }
        cur.next = cur.next.next;
        if (cur.next == null) {
            this.last = cur;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this.head;
        while (cur.next != null) {
            sb.append(cur.next.data).append(", ");
            cur = cur.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
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

package structure;

/**
 * @author lihua
 * @since 2021/10/18
 */
public class ListNode {

	public int val;

	public ListNode next;

	public ListNode(int x) {
		val = x;
		next = null;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

package base;//请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
//
// 实现 base.MyStack 类：
//
//
// void push(int x) 将元素 x 压入栈顶。
// int pop() 移除并返回栈顶元素。
// int top() 返回栈顶元素。
// boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
//
//
//
//
// 注意：
//
//
// 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
// 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
//
//
//
//
// 示例：
//
//
//输入：
//["base.MyStack", "push", "push", "top", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 2, 2, false]
//
//解释：
//base.MyStack myStack = new base.MyStack();
//myStack.push(1);
//myStack.push(2);
//myStack.top(); // 返回 2
//myStack.pop(); // 返回 2
//myStack.empty(); // 返回 False
//
//
//
//
// 提示：
//
//
// 1 <= x <= 9
// 最多调用100 次 push、pop、top 和 empty
// 每次调用 pop 和 top 都保证栈不为空
//
//
//
//
// 进阶：你能否实现每种操作的均摊时间复杂度为 O(1) 的栈？换句话说，执行 n 个操作的总时间复杂度 O(n) ，尽管其中某个操作可能需要比其他操作更长的
//时间。你可以使用两个以上的队列。
// Related Topics 栈 设计 队列

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lihua
 * @since 2021/10/25
 */
public class MyStack {

    private Queue<Integer> queue;

    private int top;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
        top = x;
    }

    public int pop() {
        int size = queue.size();
        if (size == 1) {
            top = 0;
            return queue.remove();
        }
        // 2的原因是，需要记录pop之后的下一个栈顶元素
        while (size > 2) {
            // 将队头的元素一一加到队尾去，实现倒序
            queue.add(queue.remove());
            size--;
        }
        top = queue.peek();
        queue.add(queue.remove());
        return queue.remove();
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        int top = stack.top();
        assert top == 2;
        int pop = stack.pop();
        assert pop == 2;
        boolean empty = stack.empty();
        assert !empty;
    }
}

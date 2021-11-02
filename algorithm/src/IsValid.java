//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
//
//
// 示例 1：
//
//
//输入：s = "()"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "()[]{}"
//输出：true
//
//
// 示例 3：
//
//
//输入：s = "(]"
//输出：false
//
//
// 示例 4：
//
//
//输入：s = "([)]"
//输出：false
//
//
// 示例 5：
//
//
//输入：s = "{[]}"
//输出：true
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 仅由括号 '()[]{}' 组成
//
// Related Topics 栈 字符串

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lihua
 * @since 2021/11/2
 */
public class IsValid {

    private static final Map<Character, Character> MAP = new HashMap<Character, Character>() {{
        put('}', '{');
        put(']', '[');
        put(')', '(');
    }};

    public boolean isValid(String s) {
        if (s.length() == 1) {
            return false;
        }
        // 长度为奇数肯定不是合法的括号
        if (s.length() % 2 != 0) {
            return false;
        }
        // 后遇到的左括号要先闭合，所以可以用栈来实现
        Deque<Character> deque = new ArrayDeque<>();
        return isValid(deque, s);
    }

    private boolean isValid(Deque<Character> deque, String s) {
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (MAP.containsKey(ch)) {
                // 如果先进来的是右括号，那肯定不是合法的括号
                if (deque.isEmpty()) {
                    return false;
                }
                // 将栈中最新的左括号pop出来匹配，如果匹配上了就和右括号一起扔掉，继续往后匹配
                char left = deque.pop();
                if (MAP.get(ch) == left) {
                    continue;
                }
                return false;
            } else {
                // 左括号直接入栈，等待后续匹配
                deque.push(ch);
            }
        }
        // 如果还有剩余元素，证明左括号没有被匹配完
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        IsValid valid = new IsValid();
        boolean flag = valid.isValid("({");
        assert !flag;
    }
}

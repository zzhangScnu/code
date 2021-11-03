package backtracking;//给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
//
// 返回所有可能的结果。答案可以按 任意顺序 返回。
//
//
//
// 示例 1：
//
//
//输入：s = "()())()"
//输出：["(())()","()()()"]
//
//
// 示例 2：
//
//
//输入：s = "(a)())()"
//输出：["(a())()","(a)()()"]
//
//
// 示例 3：
//
//
//输入：s = ")("
//输出：[""]
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 25
// s 由小写英文字母以及括号 '(' 和 ')' 组成
// s 中至多含 20 个括号
//
// Related Topics 广度优先搜索 字符串 回溯

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihua
 * @since 2021/11/2
 */
public class RemoveInvalidParentheses {

    private int removeLeft = 0;

    private int removeRight = 0;

    private List<String> resultList = new ArrayList<>();

    public List<String> removeInvalidParentheses(String s) {
        countRemove(s);
        backtrack(s, removeLeft, removeRight, 0);
        return resultList;
    }

    /**
     * 计算左 / 右括号最少应该删除的数量
     */
    private void countRemove(String s) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                removeLeft++;
            } else if (ch == ')') {
                if (removeLeft > 0) {
                    removeLeft--;
                } else {
                    removeRight++;
                }
            }
        }
    }

    /**
     * start存在的意义是去重
     */
    private void backtrack(String s, int left, int right, int start) {
        if (left == 0 && right == 0) {
            if (isValid(s)) {
                resultList.add(String.join("", s));
            }
            return;
        }
        int length = s.length();
        for (int i = start; i < length; i++) {
            // 重复的左 / 右括号，因为一层只能删一个字符，无论删哪个都是一样的（脑补一下搜索树，由s中的左右括号和字母组成）
            // 所以跳过重复的，删掉一个就进入下一层搜索
            if (i != start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            char ch = s.charAt(i);
            // 尝试去掉这个左括号
            if (ch == '(' && removeLeft > 0) {
                backtrack(s.substring(0, i) + s.substring(i + 1), left - 1, right, i);
            } else if (ch == ')' && removeRight > 0) {
                backtrack(s.substring(0, i) + s.substring(i + 1), left, right - 1, i);
            }
        }
    }

    private boolean isValid(String s) {
        int balance = 0;
        int length = s.length();
        char ch;
        for (int i = 0; i < length; i++) {
            ch = s.charAt(i);
            if (ch == ')') {
                balance--;
                if (balance < 0) {
                    return false;
                }
            } else if (ch == '(') {
                balance++;
            }
        }
        // 如果还有剩余元素，证明左括号没有被匹配完
        return balance == 0;
    }

    public static void main(String[] args) {
//        List<String> resultList = new backtracking.RemoveInvalidParentheses().removeInvalidParentheses("()())()");
//        assert resultList.size() == 2;
//        resultList = new backtracking.RemoveInvalidParentheses().removeInvalidParentheses("(a)())()");
//        assert resultList.size() == 2;
//        resultList = new backtracking.RemoveInvalidParentheses().removeInvalidParentheses(")(");
//        assert resultList.size() == 1;
        List<String> resultList = new RemoveInvalidParentheses().removeInvalidParentheses("((((((((((((((((((((aaaaa");
        assert resultList.size() == 1;
    }
}

//给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lihua
 * @since 2021/11/2
 */
public class RemoveInvalidParentheses {

    private int maxSize = 0;

    private HashSet<String> resultSet = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        LinkedList<String> track = new LinkedList<>();
        backtrack(s, track, 0);
        return new ArrayList<>(resultSet);
    }

    private void backtrack(String s, LinkedList<String> track, int index) {
        if (index == s.length()) {
            if (isValid(track) && track.size() >= maxSize) {
                maxSize = track.size();
                resultSet.add(String.join("", track));
            }
            return;
        }
        char ch = s.charAt(index);
        track.add(Character.toString(ch));
        backtrack(s, track, index + 1);
        track.removeLast();
        backtrack(s, track, index + 1);
    }

    private boolean isValid(LinkedList<String> track) {
        int balance = 0;
        for (String str : track) {
            if (")".equals(str)) {
                balance--;
                if (balance < 0) {
                    return false;
                }
            } else if ("(".equals(str)) {
                balance++;
            }
        }
        // 如果还有剩余元素，证明左括号没有被匹配完
        return balance == 0;
    }

    public static void main(String[] args) {
        List<String> resultList = new RemoveInvalidParentheses().removeInvalidParentheses("()())()");
        assert resultList.size() == 2;
        resultList = new RemoveInvalidParentheses().removeInvalidParentheses("(a)())()");
        assert resultList.size() == 2;
        resultList = new RemoveInvalidParentheses().removeInvalidParentheses(")(");
        assert resultList.size() == 1;
        resultList = new RemoveInvalidParentheses().removeInvalidParentheses(")(f");
        assert resultList.size() == 1;
    }
}

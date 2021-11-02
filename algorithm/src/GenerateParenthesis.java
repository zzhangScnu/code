import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 有效括号组合需满足：左括号必须以正确的顺序闭合。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：["()"]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
// Related Topics 字符串 动态规划 回溯

/**
 * @author lihua
 * @since 2021/11/1
 */
public class GenerateParenthesis {

    private List<String> resultList = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        LinkedList<String> track = new LinkedList<>();
        // left / right用于剪枝，表示还剩下多少左 / 右括号可以放置，也用于代替原有的指示track位置的index
        backtrack(track, n, n);
        return resultList;
    }

    private void backtrack(LinkedList<String> track, int left, int right) {
        // 在任意位置，都应该有左括号数量 >= 右括号数量
        if (right < left) {
            return;
        }
        if (left < 0 || right < 0) {
            return;
        }
        if (left == 0 && right == 0) {
            resultList.add(String.join("", track));
            return;
        }
        // 对index位置，放置左括号的情况
        track.add("(");
        backtrack(track, left - 1, right);
        track.removeLast();
        // 对index位置，放置右括号的情况
        track.add(")");
        backtrack(track, left, right - 1);
        track.removeLast();
    }

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        int n = 3;
        List<String> resultList = generateParenthesis.generateParenthesis(n);
        assert resultList.size() == 5;
    }
}

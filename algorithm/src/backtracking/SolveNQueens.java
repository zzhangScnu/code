package backtracking;

import java.util.ArrayList;
import java.util.List;
//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
//
//
//
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
//
//
// 示例 1：
//
//
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[["Q"]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 9
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
//
//
//
// Related Topics 数组 回溯

/**
 * @author lihua
 * @since 2021/10/26
 */
public class SolveNQueens {

    private List<List<String>> resultList = new ArrayList<>();

    private int length;

    private static final String QUEEN = "Q";

    private static final String N = ".";

    public List<List<String>> solveNQueens(int n) {
        length = n;
        int[][] playground = new int[n][n];
        backtrack(playground, 0);
        return resultList;
    }

    private void backtrack(int[][] playground, int row) {
        if (row == length) {
            resultList.add(convert(playground));
            return;
        }
        for (int col = 0; col < length; col++) {
            if (!isValid(playground, row, col)) {
                continue;
            }
            playground[row][col] = 1;
            backtrack(playground, row + 1);
            playground[row][col] = 0;
        }
    }

    private List<String> convert(int[][] playground) {
        List<String> situation = new ArrayList<>();
        StringBuilder row;
        for (int i = 0; i < length; i++) {
            row = new StringBuilder();
            for (int j = 0; j < length; j++) {
                row.append(playground[i][j] == 1 ? QUEEN : N);
            }
            situation.add(row.toString());
        }
        return situation;
    }

    private boolean isValid(int[][] playground, int row, int col) {
        // 检查列是否有冲突
        for (int k = 0; k < length; k++) {
            if (playground[k][col] == 1) {
                return false;
            }
        }
        // 检查右上方是否有冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < length; i--, j++) {
            if (playground[i][j] == 1) {
                return false;
            }
        }
        // 检查左上方是否有冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (playground[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SolveNQueens nQueens = new SolveNQueens();
        List<List<String>> resultList = nQueens.solveNQueens(4);
        assert resultList.size() == 2;
    }
}

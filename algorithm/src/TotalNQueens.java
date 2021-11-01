//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
//
//
//
//
//
// 示例 1：
//
//
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：1
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
// Related Topics 回溯

/**
 * @author lihua
 * @since 2021/11/1
 */
@SuppressWarnings("DuplicatedCode")
public class TotalNQueens {

    private int result = 0;

    public int totalNQueens(int n) {
        int[][] board = new int[n][n];
        backtrack(board, n, 0);
        return result;
    }

    /**
     * 不能用解数独那种传参的方式，因为这里需要找到所有的解法，即需要对每个空格都遍历n次，而解数独那种只有一次
     */
    private void backtrack(int[][] board, int n, int row) {
        if (row == n) {
            result++;
            return;
        }
        // 对每一个row，进行col的遍历
        for (int col = 0; col < n; col++) {
            if (!isValid(board, n, row, col)) {
                continue;
            }
            board[row][col] = 1;
            // 继续下一行的放置
            backtrack(board, n, row + 1);
            board[row][col] = 0;
        }
    }

    /**
     * 可以用三个集合代表列、左上方、右上方是否已经放置皇后
     * 使得查找效率由O(N)变为O(1)
     * 可以根据两点的横纵坐标判断是否处于某条斜线上
     */
    private boolean isValid(int[][] board, int n, int row, int col) {
        for (int i = 0; i < n; i++) {
            // 同行是否已放置
            if (board[row][i] == 1) {
                return false;
            }
            // 同列是否已放置
            if (board[i][col] == 1) {
                return false;
            }
        }
        // 检查右上方是否有冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        // 检查左上方是否有冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TotalNQueens nQueens = new TotalNQueens();
        int result = nQueens.totalNQueens(4);
        assert result == 2;
    }
}

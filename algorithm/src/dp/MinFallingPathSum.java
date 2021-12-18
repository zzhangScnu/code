package dp;

// 931-下降路径最小和
// minimum-falling-path-sum
//给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
//
// 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第
//一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1
//, col + 1) 。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
//输出：13
//解释：下面是两条和最小的下降路径，用加粗+斜体标注：
//[[2,1,3],      [[2,1,3],
// [6,5,4],       [6,5,4],
// [7,8,9]]       [7,8,9]]
//
//
// 示例 2：
//
//
//输入：matrix = [[-19,57],[-40,-5]]
//输出：-59
//解释：下面是一条和最小的下降路径，用加粗+斜体标注：
//[[-19,57],
// [-40,-5]]
//
//
// 示例 3：
//
//
//输入：matrix = [[-48]]
//输出：-48
//
//
//
//
// 提示：
//
//
// n == matrix.length
// n == matrix[i].length
// 1 <= n <= 100
// -100 <= matrix[i][j] <= 100
//
// Related Topics 数组 动态规划 矩阵

import java.util.Arrays;

/**
 *
 * @author lihua
 * @since 2021/12/17
 */
public class MinFallingPathSum {

    /**
     * 大于最大可能的路径和(10000)
     */
    private static final int INITIAL_NUM = 11111;

    /**
     * row..col的最小路径
     */
    private int[][] sum;

    private int length;

    private int result = INITIAL_NUM;

    public int minFallingPathSum(int[][] matrix) {
        length = matrix.length;
        initialSum();
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                fillSum(matrix, row, col);
                if (row == length - 1) {
                    // 这样做其实不行，不能保证是一条完整的下降路径
                    result = Math.min(result, sum[row][col]);
                }
            }
        }
        return result;
    }

    private void fillSum(int[][] matrix, int row, int col) {
        if (row == 0) {
            sum[0][col] = matrix[0][col];
            return;
        }
        if (sum[row][col] != INITIAL_NUM) {
            return;
        }
        // T字型的回溯路径
        int topLeft = valid(row - 1, col) ? matrix[row - 1][col] : INITIAL_NUM;
        int top = valid(row - 1, col - 1) ? matrix[row - 1][col - 1] : INITIAL_NUM;
        int topRight = valid(row - 1, col + 1) ? matrix[row - 1][col + 1] : INITIAL_NUM;
        int currentMin = matrix[row][col] + min(topLeft, top, topRight);
        sum[row][col] = currentMin;
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    private boolean valid(int row, int col) {
        return row >= 0 && row < length
                && col >= 0 && col < length;
    }

    private void initialSum() {
        sum = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(sum[i], INITIAL_NUM);
        }
    }

    public static void main(String[] args) {
        MinFallingPathSum clazz = new MinFallingPathSum();
        int[][] matrix = new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        int result = clazz.minFallingPathSum(matrix);
        assert result == 13;
    }
}

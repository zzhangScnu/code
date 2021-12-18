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
 * @author lihua
 * @since 2021/12/17
 */
public class MinFallingPathSum {

    /**
     * 大于最大可能的路径和(10000)
     */
    private static final int INITIAL_NUM = 11111;

    /**
     * sum[m][n]表示：
     * 从[0, b]到[m, n]的最小路径
     * 即第一行的任意一列到[m, n]的最小路径
     */
    private int[][] sum;

    private int length;

    private int result = INITIAL_NUM;

    public int minFallingPathSum(int[][] matrix) {
        length = matrix.length;
        initialSum();
        find(matrix);
        for (int col = 0; col < length; col++) {
            // 结果应该是落在最后一行的某一列上
            result = Math.min(result, sum[length - 1][col]);
        }
        return result;
    }

    private void find(int[][] matrix) {
        // base case是第一行的任意一列
        System.arraycopy(matrix[0], 0, sum[0], 0, length);
        for (int row = 1; row < length; row++) {
            for (int col = 0; col < length; col++) {
                // T字型的回溯路径
                int topLeft = col >= 1 ? sum[row - 1][col - 1] : INITIAL_NUM;
                int top = sum[row - 1][col];
                int topRight = col < length - 1 ? sum[row - 1][col + 1] : INITIAL_NUM;
                sum[row][col] = matrix[row][col] + min(topLeft, top, topRight);
            }
        }
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
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

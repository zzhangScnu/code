package dp;

// 64-最小路径和
// minimum-path-sum
//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//
// 说明：每次只能向下或者向右移动一步。
//
//
//
// 示例 1：
//
//
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
//
//
// 示例 2：
//
//
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 200
// 0 <= grid[i][j] <= 100
//
// Related Topics 数组 动态规划 矩阵

import java.util.Arrays;

/**
 * "一般来说，在二维矩阵中求最优化问题（最大值或者最小值），肯定需要递归 + 备忘录，也就是动态规划技巧。"
 * 重要的是，确定好dp数组的定义，在此基础上定义状态转移方程和遍历方向
 *
 * @author lihua
 * @since 2021/12/18
 */
public class MinPathSum {

    private static final int INITIAL_NUM = 66666;

    /**
     * sum[row][col]表示：
     * 从[0, 0]到[row, col]任意路径的最小值
     */
    private int[][] sum;

    private int rowLength;

    private int colLength;

    public int minPathSum(int[][] grid) {
        rowLength = grid.length;
        colLength = grid[0].length;
        initializeSum();
        find(grid);
        return sum[rowLength - 1][colLength - 1];
    }

    private void find(int[][] grid) {
        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                // base case是起点[0,0]
                if (row == 0 && col == 0) {
                    sum[0][0] = grid[0][0];
                    continue;
                }
                // 反L型
                // 如果列是0，说明是从上面走下来的
                int left = col >= 1 ? sum[row][col - 1] : INITIAL_NUM;
                // 如果行是0，说明是说左边走过来的
                int up = row >= 1 ? sum[row - 1][col] : INITIAL_NUM;
                int currentMinSum = grid[row][col] + Math.min(left, up);
                sum[row][col] = currentMinSum;
            }
        }
    }

    private void initializeSum() {
        sum = new int[rowLength][colLength];
        for (int i = 0; i < rowLength; i++) {
            Arrays.fill(sum[i], INITIAL_NUM);
        }
    }

    /**
     * 一定要左上角到右下角
     * 每次向右或者向下
     */
    public static void main(String[] args) {
        MinPathSum clazz = new MinPathSum();
        // 1→3→1→1→1
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int result = clazz.minPathSum(grid);
        assert result == 7;
    }
}

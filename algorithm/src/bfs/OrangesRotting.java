package bfs;
// 994-腐烂的橘子
// rotting-oranges
//在给定的网格中，每个单元格可以有以下三个值之一：
//
//
// 值 0 代表空单元格；
// 值 1 代表新鲜橘子；
// 值 2 代表腐烂的橘子。
//
//
// 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
//
// 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
//
//
//
// 示例 1：
//
//
//
// 输入：[[2,1,1],[1,1,0],[0,1,1]]
//输出：4
//
//
// 示例 2：
//
// 输入：[[2,1,1],[0,1,1],[1,0,1]]
//输出：-1
//解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
//
//
// 示例 3：
//
// 输入：[[0,2]]
//输出：0
//解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
//
//
//
//
// 提示：
//
//
// 1 <= grid.length <= 10
// 1 <= grid[0].length <= 10
// grid[i][j] 仅为 0、1 或 2
//
// Related Topics 广度优先搜索 数组 矩阵

import java.util.LinkedList;
import java.util.Queue;

/**
 * 多源广度优先搜索
 *
 * @author lihua
 * @since 2021/11/15
 */
public class OrangesRotting {

    private static final int[] ROW_MOVEMENT = {-1, 0, 1, 0};

    private static final int[] COL_MOVEMENT = {0, -1, 0, 1};

    private int rowSize;

    private int colSize;

    public int orangesRotting(int[][] grid) {
        if (allRotted(grid)) {
            return 0;
        }
        rowSize = grid.length;
        colSize = grid[0].length;
        // row * colSize + col 可以唯一确定一个坐标
        Queue<Integer> queue = initial(grid);
        int levelSize;
        int minutes = 0;
        int current, row, col;
        while (!queue.isEmpty()) {
            if (allRotted(grid)) {
                return minutes;
            }
            levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                current = queue.poll();
                row = current / colSize;
                col = current % colSize;
                if (grid[row][col] == 2) {
                    doRotted(grid, row, col, queue);
                }
            }
            minutes++;
        }
        return -1;
    }

    private void doRotted(int[][] grid, int row, int col, Queue<Integer> queue) {
        int nextRow, nextCol, nextCoordinate;
        // 四个方向移动
        for (int i = 0; i < 4; i++) {
            nextRow = row + ROW_MOVEMENT[i];
            nextCol = col + COL_MOVEMENT[i];
            nextCoordinate = nextRow * colSize + nextCol;
            if (canRotted(nextRow, nextCol, grid)) {
                grid[nextRow][nextCol] = 2;
                queue.add(nextCoordinate);
            }
        }
    }

    /**
     * 这里不需要visited判断是否走回头路
     * 因为只有未腐烂和腐烂两种状态，且无法逆转
     */
    private boolean canRotted(int nextRow, int nextCol, int[][] grid) {
        return nextRow >= 0 && nextRow < rowSize
                && nextCol >= 0 && nextCol < colSize
                && grid[nextRow][nextCol] == 1;
    }

    private Queue<Integer> initial(int[][] grid) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(i * colSize + j);
                }
            }
        }
        return queue;
    }

    /**
     * 用未腐烂的橘子count来做标识，省去每次双重循环的判断，应该会更快
     */
    private boolean allRotted(int[][] grid) {
        for (int[] row : grid) {
            for (int element : row) {
                if (element == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        OrangesRotting clazz = new OrangesRotting();
        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int result = clazz.orangesRotting(grid);
        assert result == 4;
        assert new OrangesRotting().orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}) == -1;
        assert new OrangesRotting().orangesRotting(new int[][]{{0, 2}}) == 0;
        assert new OrangesRotting().orangesRotting(new int[][]{{0}}) == 0;
    }
}

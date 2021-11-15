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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author lihua
 * @since 2021/11/15
 */
public class OrangesRotting {

    public int orangesRotting(int[][] grid) {
        if (allRotted(grid)) {
            return 0;
        }
        int rowSize = grid.length;
        int colSize = grid[0].length;
        Queue<int[]> queue = initial(grid);
        Set<int[]> visited = new HashSet<>();
        int levelSize;
        int minutes = 0;
        int[] current;
        while (!queue.isEmpty()) {
            if (allRotted(grid)) {
                return minutes;
            }
            levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                current = queue.poll();
                if (grid[current[0]][current[1]] == 2) {
                    doRotted(grid, rowSize, colSize, queue, visited, current);
                }
            }
            minutes++;
        }
        return -1;
    }

    private void doRotted(int[][] grid, int rowSize, int colSize, Queue<int[]> queue, Set<int[]> visited, int[] current) {
        if (current[0] > 0 && grid[current[0] - 1][current[1]] == 1 && !visited.contains(new int[]{current[0] - 1, current[1]})) {
            grid[current[0] - 1][current[1]] = 2;
            visited.add(new int[]{current[0] - 1, current[1]});
            queue.add(new int[]{current[0] - 1, current[1]});
        }
        if (current[0] < rowSize - 1 && grid[current[0] + 1][current[1]] == 1 && !visited.contains(new int[]{current[0] + 1, current[1]})) {
            grid[current[0] + 1][current[1]] = 2;
            visited.add(new int[]{current[0] + 1, current[1]});
            queue.add(new int[]{current[0] + 1, current[1]});
        }
        if (current[1] > 0 && grid[current[0]][current[1] - 1] == 1 && !visited.contains(new int[]{current[0], current[1] - 1})) {
            grid[current[0]][current[1] - 1] = 2;
            visited.add(new int[]{current[0], current[1] - 1});
            queue.add(new int[]{current[0], current[1] - 1});
        }
        if (current[1] < colSize - 1 && grid[current[0]][current[1] + 1] == 1 && !visited.contains(new int[]{current[0], current[1] + 1})) {
            grid[current[0]][current[1] + 1] = 2;
            visited.add(new int[]{current[0], current[1] + 1});
            queue.add(new int[]{current[0], current[1] + 1});
        }
    }

    private Queue<int[]> initial(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        return queue;
    }

    private boolean allRotted(int[][] grid) {
        for (int[] row : grid) {
            for (int element : row) {
                if (element != 0 && element != 2) {
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
        assert clazz.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}) == -1;
        assert clazz.orangesRotting(new int[][]{{0, 2}}) == 0;
        assert clazz.orangesRotting(new int[][]{{0}}) == 0;
    }
}

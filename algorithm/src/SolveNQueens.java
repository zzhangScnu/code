import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
        int[][] playground = initialPlayground(n);
        List<List<String>> track = new ArrayList<>();
        backtrack(playground, track);
        return resultList;
    }

    /**
     * playground[i][j]为1时，表示放置了皇后
     */
    private int[][] initialPlayground(int n) {
        int[][] playground = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                playground[i][j] = 0;
            }
        }
        return playground;
    }

    private void backtrack(int[][] playground, List<List<String>> track) {
        if (track.size() == length) {
            resultList.add(convert(track));
            return;
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (!isValid(playground, i, j)) {
                    continue;
                }
                if (track.size() < i + 1) {
                    List<String> row = new ArrayList<>();
                    for (int k = 0; k < length; k++) {
                        row.add(N);
                    }
                    track.add(row);
                }
                track.get(i).set(j, QUEEN);
                playground[i][j] = 1;
                backtrack(playground, track);
                track.get(i).set(j, N);
                playground[i][j] = 0;
            }
        }
    }

    private List<String> convert(List<List<String>> track) {
        return track.stream()
                .map(row -> String.join("", row))
                .collect(Collectors.toList());
    }

    private boolean isValid(int[][] playground, int i, int j) {
        for (int k = 0; k < length; k++) {
            if (playground[i][k] == 1 || playground[k][j] == 1) {
                return false;
            }
        }
        for (int m = 0, n = 0; m < length; m++, n++) {
            if (playground[m][n] == 1) {
                return false;
            }
        }
        for (int m = 0, n = length - 1; m < length; m++, n--) {
            if (playground[m][n] == 1) {
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

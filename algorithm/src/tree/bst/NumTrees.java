package tree.bst;

// 96-不同的二叉搜索树
// unique-binary-search-trees
//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：5
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
// 1 <= n <= 19
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树

/**
 * @author lihua
 * @since 2021/11/30
 */
public class NumTrees {

    public int numTrees(int n) {
        // 备忘录，减少重复子问题的递归
        int[][] subTreeRes = new int[n + 1][n + 1];
        return numTrees(1, n, subTreeRes);
    }

    private int numTrees(int low, int high, int[][] subTreeRes) {
        // 空区间代表空节点，这也是一种情况
        if (low > high) {
            return 1;
        }
        int result = 0;
        int left, right;
        if (subTreeRes[low][high] != 0) {
            result += subTreeRes[low][high];
            return result;
        }
        for (int pivot = low; pivot <= high; pivot++) {
            left = numTrees(low, pivot - 1, subTreeRes);
            right = numTrees(pivot + 1, high, subTreeRes);
            // 左子树和右子树排列组合的结果数
            result += left * right;
            subTreeRes[low][high] = result;
        }
        return result;
    }
}

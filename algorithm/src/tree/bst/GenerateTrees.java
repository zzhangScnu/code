package tree.bst;

import structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

// 95-不同的二叉搜索树 II
// unique-binary-search-trees-ii
//给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
//
//
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
//
//
// Related Topics 树 二叉搜索树 动态规划 回溯 二叉树

/**
 * @author lihua
 * @since 2021/11/30
 */
public class GenerateTrees {

    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int low, int high) {
        List<TreeNode> result = new ArrayList<>();
        if (low > high) {
            result.add(null);
            return result;
        }
        // 遍历可选值，固定一个根节点
        for (int pivot = low; pivot <= high; pivot++) {
            List<TreeNode> leftTrees = generateTrees(low, pivot - 1);
            List<TreeNode> rightTrees = generateTrees(pivot + 1, high);
            // 固定根节点的情况下，排列组合左右子树
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(pivot);
                    root.left = leftTree;
                    root.right = rightTree;
                    result.add(root);
                }
            }
        }
        return result;
    }
}

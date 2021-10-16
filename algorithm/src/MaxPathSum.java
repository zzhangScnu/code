import structure.TreeNode;

import java.util.Objects;

//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。
//
// 路径和 是路径中各节点值的总和。
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
//
// 示例 2：
//
//
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
//
//
//
//
// 提示：
//
//
// 树中节点数目范围是 [1, 3 * 104]
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 动态规划 二叉树
/**
 * @author lihua
 * @since 2021/10/16
 */
public class MaxPathSum {

    /**
     * 节点中有负数
     */
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        doMaxPathSum(root);
        return maxSum;
    }

    private int doMaxPathSum(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        // 如果有子树最大路径和小于0的，就不要算到总的最大路径和里面去了
        int left = Math.max(0, doMaxPathSum(root.left));
        int right = Math.max(0, doMaxPathSum(root.right));
        maxSum = Math.max(maxSum, root.val + left + right);
        // 继续往下找，可能会有更长的路径存在
        return Math.max(root.val + left, root.val + right);
    }
}

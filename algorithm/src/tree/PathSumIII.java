package tree;

import structure.TreeNode;

import java.util.Objects;

//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
//
//
// 示例 2：
//
//
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
//
//
//
//
// 提示:
//
//
// 二叉树的节点个数的范围是 [0,1000]
// -109 <= Node.val <= 109
// -1000 <= targetSum <= 1000
//
// Related Topics 树 深度优先搜索 二叉树

/**
 * @author lihua
 * @since 2021/10/15
 */
public class PathSumIII {

    int count = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (Objects.isNull(root)) {
            return count;
        }
        doPathSum(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return count;
    }

    private void doPathSum(TreeNode root, int targetSum) {
        if (Objects.isNull(root)) {
            return;
        }
        // 只要其中一个节点满足查找条件了，就表示找到了一条这样的路径
        if (root.val == targetSum) {
            count++;
        }
        int newTargetSum = targetSum - root.val;
        doPathSum(root.left, newTargetSum);
        doPathSum(root.right, newTargetSum);
    }
}

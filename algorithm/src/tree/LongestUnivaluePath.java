package tree;//给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
//
// 注意：两个节点之间的路径长度由它们之间的边数表示。
//
// 示例 1:
//
// 输入:
//
//
//              5
//             / \
//            4   5
//           / \   \
//          1   1   5
//
//
// 输出:
//
//
//2
//
//
// 示例 2:
//
// 输入:
//
//
//              1
//             / \
//            4   5
//           / \   \
//          4   4   5
//
//
// 输出:
//
//
//2
//
//
// 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
// Related Topics 树 深度优先搜索 二叉树

import structure.TreeNode;

import java.util.Objects;

/**
 * @author lihua
 * @since 2021/10/17
 */
public class LongestUnivaluePath {

    int length = 0;

    public int longestUnivaluePath(TreeNode root) {
        doLongestUnivaluePath(root);
        return length;
    }

    private int doLongestUnivaluePath(TreeNode p) {
        if (Objects.isNull(p)) {
            return 0;
        }
        int left = doLongestUnivaluePath(p.left);
        int right = doLongestUnivaluePath(p.right);
        // 如果左节点和本节点值相同，则把左子树的长度加上；否则就算左子树再长也没用，不属于同值路径，直接计为0
        if (Objects.nonNull(p.left) && p.val == p.left.val) {
            left++;
        } else {
            left = 0;
        }
        if (Objects.nonNull(p.right) && p.val == p.right.val) {
            right++;
        } else {
            right = 0;
        }
        length = Math.max(length, left + right);
        // 取左右子树中最长的一棵
        return Math.max(left, right);
    }
}

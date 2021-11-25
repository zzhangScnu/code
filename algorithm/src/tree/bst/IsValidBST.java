package tree.bst;

import structure.TreeNode;

// 98-验证二叉搜索树
// validate-binary-search-tree
//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
//
// 有效 二叉搜索树定义如下：
//
//
// 节点的左子树只包含 小于 当前节点的数。
// 节点的右子树只包含 大于 当前节点的数。
// 所有左子树和右子树自身必须也是二叉搜索树。
//
//
//
//
// 示例 1：
//
//
//输入：root = [2,1,3]
//输出：true
//
//
// 示例 2：
//
//
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
//
//
//
//
// 提示：
//
//
// 树中节点数目范围在[1, 104] 内
// -231 <= Node.val <= 231 - 1
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树

/**
 * @author lihua
 * @since 2021/11/25
 */
public class IsValidBST {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && min.val >= root.val) {
            return false;
        }
        if (max != null && max.val <= root.val) {
            return false;
        }
        // 当前节点作为根节点的BST的最小值(即最左的节点)，即使在左子树中也是最小的；
        // 而root的值会比左子树的最大值都要大
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }
}

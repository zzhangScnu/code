package tree;
// 226-翻转二叉树
// invert-binary-tree
//翻转一棵二叉树。
//
// 示例：
//
// 输入：
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9
//
// 输出：
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1
//
// 备注:
//这个问题是受到 Max Howell 的 原问题 启发的 ：
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树
// 👍 1063 👎 0

import structure.TreeNode;

/**
 * @author lihua
 * @since 2021/11/16
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        doInvertTree(root);
        return root;
    }

    private void doInvertTree(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null || root.right != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        doInvertTree(root.left);
        doInvertTree(root.right);
    }
}

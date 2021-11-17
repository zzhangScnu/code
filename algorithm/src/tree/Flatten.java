package tree;

// 114-二叉树展开为链表
// flatten-binary-tree-to-linked-list
//给你二叉树的根结点 root ，请你将它展开为一个单链表：
//
//
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。
//
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 树中结点数在范围 [0, 2000] 内
// -100 <= Node.val <= 100
//
//
//
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
// Related Topics 栈 树 深度优先搜索 链表 二叉树

import structure.TreeNode;

/**
 * @author lihua
 * @since 2021/11/17
 */
public class Flatten {

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        // 拉直左子树为一条中序遍历的斜线
        flatten(left);
        flatten(right);
        root.left = null;
        // 将拉直后的左子树衔接上去
        root.right = left;
        // 遍历到当前树的最右下角的节点
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        // 将拉直后的右子树衔接上去
        cur.right = right;
    }
}

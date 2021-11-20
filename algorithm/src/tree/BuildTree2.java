package tree;

// 106-从中序与后序遍历序列构造二叉树
// construct-binary-tree-from-inorder-and-postorder-traversal
//根据一棵树的中序遍历与后序遍历构造二叉树。
//
// 注意:
//你可以假设树中没有重复的元素。
//
// 例如，给出
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3]
//
// 返回如下的二叉树：
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// Related Topics 树 数组 哈希表 分治 二叉树

import structure.TreeNode;

/**
 * @author lihua
 * @since 2021/11/19
 */
public class BuildTree2 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int inLow, int inHigh,
                               int[] postorder, int postLow, int postHigh) {
        // base case要记得写
        if (postLow > postHigh) {
            return null;
        }
        // 后序遍历序列中逐个找根节点
        int rootVal = postorder[postHigh];
        TreeNode root = new TreeNode(rootVal);
        int inPivot = 0;
        for (int i = inLow; i <= inHigh; i++) {
            if (inorder[i] == rootVal) {
                inPivot = i;
                break;
            }
        }
        int leftSize = inPivot - inLow;
        root.left = buildTree(inorder, inLow, inPivot - 1,
                postorder, postLow, postLow + leftSize - 1);
        root.right = buildTree(inorder, inPivot + 1, inHigh,
                postorder, postLow + leftSize, postHigh - 1);
        return root;
    }
}

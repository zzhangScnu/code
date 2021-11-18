package tree;

// 105-从前序与中序遍历序列构造二叉树
// construct-binary-tree-from-preorder-and-inorder-traversal
//给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。
//
//
//
// 示例 1:
//
//
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
//
//
// 示例 2:
//
//
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
//
//
//
//
// 提示:
//
//
// 1 <= preorder.length <= 3000
// inorder.length == preorder.length
// -3000 <= preorder[i], inorder[i] <= 3000
// preorder 和 inorder 均无重复元素
// inorder 均出现在 preorder
// preorder 保证为二叉树的前序遍历序列
// inorder 保证为二叉树的中序遍历序列
//
// Related Topics 树 数组 哈希表 分治 二叉树

import structure.TreeNode;

/**
 * @author lihua
 * @since 2021/11/18
 */
public class BuildTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return doBuildTree(preorder, inorder,
                0, preorder.length - 1,
                0, inorder.length - 1);
    }

    private TreeNode doBuildTree(int[] preorder, int[] inorder, int preLow, int preHigh, int inLow, int inHigh) {
        if (preLow > preHigh) {
            return null;
        }
        int currentRoot = preorder[preLow];
        int pivot = 0;
        // 将中序数组划分成左右两个，划分线就是pivot，也就是当前前序数组的第一个元素
        for (int j = inLow; j <= inHigh; j++) {
            if (currentRoot == inorder[j]) {
                pivot = j;
                break;
            }
        }
        // 左子树的大小
        int leftSize = pivot - inLow;
        TreeNode root = new TreeNode(currentRoot);
        root.left = doBuildTree(preorder, inorder,
                preLow + 1, preLow + leftSize,
                inLow, pivot - 1);
        root.right = doBuildTree(preorder, inorder,
                preLow + leftSize + 1, preHigh,
                pivot + 1, inHigh);
        return root;
    }
}

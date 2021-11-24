package tree.bst;

// 230-二叉搜索树中第K小的元素
// kth-smallest-element-in-a-bst
//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
//
//
//
// 示例 1：
//
//
//输入：root = [3,1,4,null,2], k = 1
//输出：1
//
//
// 示例 2：
//
//
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
//
//
//
//
//
//
// 提示：
//
//
// 树中的节点数为 n 。
// 1 <= k <= n <= 104
// 0 <= Node.val <= 104
//
//
//
//
// 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树

import structure.TreeNode;

/**
 * 可以用二分查找，将O(N)降低为O(logN)
 * 这时候需要在每个节点都维护本子树的节点数量，可以通过左子树推导出节点的大小排名
 *
 * @author lihua
 * @since 2021/11/24
 */
public class kthSmallest {

    private int result = -1;

    private int index = 0;

    public int kthSmallest(TreeNode root, int k) {
        find(root, k);
        return result;
    }

    private void find(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        kthSmallest(root.left, k);
        index++;
        if (k == index) {
            result = root.val;
            return;
        }
        kthSmallest(root.right, k);
    }
}

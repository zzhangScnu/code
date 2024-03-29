package tree;
// 111-二叉树的最小深度
// minimum-depth-of-binary-tree
//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明：叶子节点是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：2
//
//
// 示例 2：
//
//
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
//
//
//
//
// 提示：
//
//
// 树中节点数的范围在 [0, 105] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树

import structure.TreeNode;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author lihua
 * @since 2021/10/17
 */
public class MinDepth {

    /**
     * 路径上的最小节点数量
     */
    public int minDepth(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // 如果节点的左子树或者右子树不存在，可能有两种情况：
        // 1. 节点是非叶子节点。这时候left或right是0
        // 2. 节点是叶子节点。这时候left和right都为0
        // 这两种情况，都直接相加再加1，就是到本层为止的深度了
        if (Objects.isNull(root.left) || Objects.isNull(root.right)) {
            return left + right + 1;
        }
        return Math.min(left, right) + 1;
    }

    public int minDepthInBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 如果用Dequeue的话，要注意方法是在哪端操作的
        Queue<TreeNode> queue = new LinkedList<>();
        int step = 1;
        queue.offer(root);
        TreeNode current;
        int levelSize;
        while (!queue.isEmpty()) {
            levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                current = queue.poll();
                // 层序遍历碰到的第一个叶子节点，就是最短路径的终点
                if (current.left == null && current.right == null) {
                    return step;
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            step++;
        }
        return step;
    }

    public static void main(String[] args) {
        MinDepth clazz = new MinDepth();
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4, null, null), null),
                new TreeNode(3, null, new TreeNode(5, null, null)));
        int result = clazz.minDepthInBfs(root);
        assert result == 3;
    }
}

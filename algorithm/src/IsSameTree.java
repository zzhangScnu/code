//给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
//
//
//
// 示例 1：
//
//
//输入：p = [1,2,3], q = [1,2,3]
//输出：true
//
//
// 示例 2：
//
//
//输入：p = [1,2], q = [1,null,2]
//输出：false
//
//
// 示例 3：
//
//
//输入：p = [1,2,1], q = [1,1,2]
//输出：false
//
//
//
//
// 提示：
//
//
// 两棵树上的节点数目都在范围 [0, 100] 内
// -104 <= Node.val <= 104
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树
// 👍 698 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Objects;

/**
 * @author lihua
 * @since 2021/10/16
 */
public class IsSameTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 只有一个收束条件
        if (Objects.isNull(p) && Objects.isNull(q)) {
            return true;
        }
        if (Objects.isNull(p) || Objects.isNull(q)) {
            return false;
        }
        // 如果这里用==判断的话，相同就返回了，不会递归去比较子树了
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

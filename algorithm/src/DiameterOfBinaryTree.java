import structure.TreeNode;

import java.util.Objects;
//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
//
//
//
// 示例 :
//给定二叉树
//
//           1
//         / \
//        2   3
//       / \
//      4   5
//
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
//
//
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。
// Related Topics 树 深度优先搜索 二叉树

/**
 * @author lihua
 * @since 2021/10/17
 */
public class DiameterOfBinaryTree {

    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        doDiameterOfBinaryTree(root);
        // 直径是两点之间边的数量，所以等于节点数量减去1
        return diameter == 0 ? 0 : diameter - 1;
    }

    private int doDiameterOfBinaryTree(TreeNode p) {
        if (Objects.isNull(p)) {
            return 0;
        }
        int left = doDiameterOfBinaryTree(p.left);
        int right = doDiameterOfBinaryTree(p.right);
        diameter = Math.max(diameter, left + right + 1);
        return Math.max(left + 1, right + 1);
    }
}

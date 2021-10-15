import java.util.Objects;

/**
 * @author lihua
 * @since 2021/10/15
 */
public class PathSumIII {

    int count = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (Objects.isNull(root)) {
            return count;
        }
        doPathSum(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return count;
    }

    private void doPathSum(TreeNode root, int targetSum) {
        if (Objects.isNull(root)) {
            return;
        }
        // 只要其中一个节点满足查找条件了，就表示找到了一条这样的路径
        if (root.val == targetSum) {
            count++;
        }
        int newTargetSum = targetSum - root.val;
        doPathSum(root.left, newTargetSum);
        doPathSum(root.right, newTargetSum);
    }

    public static class TreeNode {

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
}

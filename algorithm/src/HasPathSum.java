import java.util.Objects;

/**
 * @author lihua
 * @since 2021/10/15
 */
public class HasPathSum {

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

	public boolean hasPathSum(TreeNode root, int targetSum) {
		if (Objects.isNull(root)) {
			return false;
		}
		// 只要根节点满足值相等的条件了，就算有这样的一条路径
		if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
			return root.val == targetSum;
		}
		int newTargetSum = targetSum - root.val;
		return hasPathSum(root.left, newTargetSum) || hasPathSum(root.right, newTargetSum);
	}
}

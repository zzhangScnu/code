import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

/**
 * @author lihua
 * @since 2021/10/15
 */
public class HasPathSumII {

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

	/**
	 * 写惯业务代码，第一次没反应过来可以用全局变量，避免当成参数传来传去
	 */
	List<List<Integer>> resultList = new ArrayList<>();

	Deque<Integer> pathList = new ArrayDeque<>();

	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		doPathSum(root, targetSum);
		return resultList;
	}

	private void doPathSum(TreeNode root, int targetSum) {
		if (Objects.isNull(root)) {
			return;
		}
		// 先记录路径
		pathList.offerLast(root.val);
		// 只要根节点满足值相等的条件了，就算有这样的一条路径
		if (Objects.isNull(root.left) && Objects.isNull(root.right) && root.val == targetSum) {
			resultList.add(new ArrayList<>(pathList));
		}
		int newTargetSum = targetSum - root.val;
		doPathSum(root.left, newTargetSum);
		doPathSum(root.right, newTargetSum);
		// 回退到上一个状态，以便遍历其他分支
		pathList.pollLast();
	}
}

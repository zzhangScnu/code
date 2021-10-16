import structure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
//
// 叶子节点 是指没有子节点的节点。
//
//
//
//
//
// 示例 1：
//
//
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
//
//
// 示例 2：
//
//
//输入：root = [1,2,3], targetSum = 5
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [1,2], targetSum = 0
//输出：[]
//
//
//
//
// 提示：
//
//
// 树中节点总数在范围 [0, 5000] 内
// -1000 <= Node.val <= 1000
// -1000 <= targetSum <= 1000
//
//
//
// Related Topics 树 深度优先搜索 回溯 二叉树

/**
 * @author lihua
 * @since 2021/10/15
 */
public class PathSumII {

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
		// 只要叶子节点满足值相等的条件了，就算有这样的一条路径
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

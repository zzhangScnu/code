import structure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
//
//
//
// 示例：
//二叉树：[3,9,20,null,null,15,7],
//
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//
// 返回其层序遍历结果：
//
//
//[
//  [3],
//  [9,20],
//  [15,7]
//]
//
// Related Topics 树 广度优先搜索 二叉树

/**
 * @author lihua
 * @since 2021/10/18
 */
public class LevelOrder {

	private List<List<Integer>> resultList = new ArrayList<>();

	/**
	 * Stack是线程安全的，而Deque不是
	 */
	private Deque<TreeNode> levelNodeStack = new ArrayDeque<>();

	public List<List<Integer>> levelOrder(TreeNode root) {
		// 注意边界条件
		if (Objects.isNull(root)) {
			return resultList;
		}
		levelNodeStack.push(root);
		doLevelOrder();
		return resultList;
	}

	private void doLevelOrder() {
		TreeNode p;
		while (!levelNodeStack.isEmpty()) {
			int sizeOfLevel = levelNodeStack.size();
			List<Integer> levelList = new ArrayList<>();
			for (int i = 0; i < sizeOfLevel; i++) {
				p = levelNodeStack.pop();
				levelList.add(p.val);
				if (Objects.nonNull(p.left)) {
					levelNodeStack.add(p.left);
				}
				if (Objects.nonNull(p.right)) {
					levelNodeStack.add(p.right);
				}
			}
			resultList.add(levelList);
		}
	}
}

package tree

import . "algorithm.com/structure"

//如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
//
// 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
//
//
//
// 示例 1：
//
//
//
// 输入：[1,1,1,1,1,null,1]
//输出：true
//
//
// 示例 2：
//
//
//
// 输入：[2,2,2,5,2]
//输出：false
//
//
//
//
// 提示：
//
//
// 给定树的节点数范围是 [1, 100]。
// 每个节点的值都是整数，范围为 [0, 99] 。
//
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树

func isUnivalTree(root *TreeNode) bool {
	val := root.Val
	var dfs func(node *TreeNode) bool
	dfs = func(node *TreeNode) bool {
		if node == nil {
			return true
		}
		return node.Val == val &&
			dfs(node.Left) &&
			dfs(node.Right)
	}
	return dfs(root)
}

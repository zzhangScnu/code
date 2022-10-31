package bst

import . "algorithm.com/structure"

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
// 1 <= k <= n <= 10⁴
// 0 <= Node.val <= 10⁴
//
//
//
//
// 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树

// 优化做法：在节点中保存子树的大小subSize，根据BST的特性可以根据本节点的subSize和左子树的subSize推断出本节点的排名
// 然后可以采用二分搜索的做法，比较大小并走不同的分支
func kthSmallest(root *TreeNode, k int) int {
	depth, res := 0, 0
	var dfs func(node *TreeNode)
	dfs = func(node *TreeNode) {
		if node == nil {
			return
		}
		dfs(node.Left)
		depth++
		if depth == k {
			res = node.Val
			return
		}
		dfs(node.Right)
	}
	dfs(root)
	return res
}

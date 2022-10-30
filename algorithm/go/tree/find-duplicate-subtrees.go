package tree

import . "algorithm.com/structure"

//给你一棵二叉树的根节点 root ，返回所有 重复的子树 。
//
// 对于同一类的重复子树，你只需要返回其中任意 一棵 的根结点即可。
//
// 如果两棵树具有 相同的结构 和 相同的结点值 ，则认为二者是 重复 的。
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [1,2,3,4,null,2,4,null,null,4]
//输出：[[2,4],[4]]
//
// 示例 2：
//
//
//
//
//输入：root = [2,1,1]
//输出：[[1]]
//
// 示例 3：
//
//
//
//
//输入：root = [2,2,2,3,null,3,null]
//输出：[[2,3],[3]]
//
//
//
// 提示：
//
//
// 树中的结点数在 [1, 5000] 范围内。
// -200 <= Node.val <= 200
//
//
// Related Topics 树 深度优先搜索 哈希表 二叉树

func findDuplicateSubtrees(root *TreeNode) []*TreeNode {
	mapping := make(map[int][]*TreeNode, 400)
	var res []*TreeNode
	var dfs func(node *TreeNode)
	dfs = func(node *TreeNode) {
		if node == nil {
			return
		}
		eqFlag := false
		val := node.Val
		candidates := mapping[val]
		for i, candidate := range candidates {
			if equals(candidate, node) {
				candidates = append(candidates[:i], candidates[i+1:]...)
				mapping[val] = candidates
				res = append(res, node)
				eqFlag = true
				break
			}
		}
		if !eqFlag {
			candidates = append(candidates, node)
			mapping[val] = candidates
		}
		dfs(node.Left)
		dfs(node.Right)
	}
	dfs(root)
	return res
}

func equals(p, q *TreeNode) bool {
	if p == nil && q == nil {
		return true
	}
	if p == nil || q == nil {
		return false
	}
	return p.Val == q.Val &&
		equals(p.Left, q.Left) &&
		equals(p.Right, q.Right)
}

package tree

import (
	. "algorithm.com/structure"
	"strconv"
)

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
	mapping := make(map[string]int, 5000)
	var res []*TreeNode
	var dfs func(node *TreeNode) string
	dfs = func(node *TreeNode) string {
		if node == nil {
			return "#"
		}
		curTree := dfs(node.Left) + "," +
			dfs(node.Right) + "," +
			strconv.Itoa(node.Val)
		if mapping[curTree] == 1 { // 重复子树只加入一次
			res = append(res, node)
		}
		mapping[curTree]++
		return curTree
	}
	dfs(root)
	return res
}

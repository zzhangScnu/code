package bst

import (
	. "algorithm.com/structure"
	"math"
)

//给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
//
// 差值是一个正数，其数值等于两值之差的绝对值。
//
//
//
// 示例 1：
//
//
//输入：root = [4,2,6,1,3]
//输出：1
//
//
// 示例 2：
//
//
//输入：root = [1,0,48,null,null,12,49]
//输出：1
//
//
//
//
// 提示：
//
//
// 树中节点的数目范围是 [2, 10⁴]
// 0 <= Node.val <= 10⁵
//
//
//
//
// 注意：本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-
//nodes/ 相同
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 二叉树

func getMinimumDifference(root *TreeNode) int {
	min := 1_00_000
	// 保存节点比值更通用
	var preValNode *TreeNode
	var dfs func(node *TreeNode)
	dfs = func(node *TreeNode) {
		if node == nil {
			return
		}
		dfs(node.Left)
		// 在中序里不能随便return，会导致右子树的遍历无法进行
		if preValNode != nil {
			curVal, preVal := node.Val, preValNode.Val
			if curVal != preVal {
				diff := calAbsDifference(curVal, preVal)
				if diff < min {
					min = diff
				}
			}
		}
		preValNode = node
		dfs(node.Right)
	}
	dfs(root)
	return min
}

func calAbsDifference(a, b int) int {
	return int(math.Abs(float64(a - b)))
}

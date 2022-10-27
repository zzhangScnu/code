package tree

import (
	. "algorithm.com/structure"
	"container/list"
)

//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明：叶子节点是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：2
//
//
// 示例 2：
//
//
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
//
//
//
//
// 提示：
//
//
// 树中节点数的范围在 [0, 10⁵] 内
// -1000 <= Node.val <= 1000
//
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树

func minDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	if root.Left == nil || root.Right == nil {
		return minDepth(root.Left) + minDepth(root.Right) + 1
	}
	return min(minDepth(root.Left), minDepth(root.Right)) + 1
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func minDepthByLevelOrder(root *TreeNode) int {
	level := 0
	if root == nil {
		return level
	}
	stack := list.New()
	stack.PushBack(root)
	for stack.Len() != 0 {
		levelLen := stack.Len()
		level++
		for i := 0; i < levelLen; i++ {
			e := stack.Front()
			stack.Remove(e)
			node := e.Value.(*TreeNode)
			if node.Left == nil && node.Right == nil { // 必须是叶子节点，第一次遍历到就是最小深度
				return level
			}
			if node.Left != nil {
				stack.PushBack(node.Left)
			}
			if node.Right != nil {
				stack.PushBack(node.Right)
			}
		}
	}
	return level
}

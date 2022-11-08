package bst

import (
	. "algorithm.com/structure"
	"math"
)

//给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
//
// 二叉搜索树的定义如下：
//
//
// 任意节点的左子树中的键值都 小于 此节点的键值。
// 任意节点的右子树中的键值都 大于 此节点的键值。
// 任意节点的左子树和右子树都是二叉搜索树。
//
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
//输出：20
//解释：键值为 3 的子树是和最大的二叉搜索树。
//
//
// 示例 2：
//
//
//
//
//输入：root = [4,3,null,1,2]
//输出：2
//解释：键值为 2 的单节点子树是和最大的二叉搜索树。
//
//
// 示例 3：
//
//
//输入：root = [-4,-2,-5]
//输出：0
//解释：所有节点键值都为负数，和最大的二叉搜索树为空。
//
//
// 示例 4：
//
//
//输入：root = [2,1,3]
//输出：6
//
//
// 示例 5：
//
//
//输入：root = [5,4,8,3,null,6,3]
//输出：7
//
//
//
//
// 提示：
//
//
// 每棵树有 1 到 40000 个节点。
// 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。
//
//
// Related Topics 树 深度优先搜索 二叉搜索树 动态规划 二叉树

func maxSumBST(root *TreeNode) int {
	sum := 0                                           // 负数和则返回0
	var dfs func(node *TreeNode) (bool, int, int, int) // 是否合法二叉搜索树；树的键值对之和；树的最小节点值；树的最大节点值
	dfs = func(node *TreeNode) (bool, int, int, int) {
		if node == nil {
			return true, 0, math.MaxInt, math.MinInt
		}
		lFlag, lSum, lMin, lMax := dfs(node.Left)
		rFlag, rSum, rMin, rMax := dfs(node.Right)
		val := node.Val
		if lFlag && rFlag && val > lMax && val < rMin {
			curTreeSum := lSum + rSum + val
			if curTreeSum > sum {
				sum = curTreeSum
			}
			return true, curTreeSum, min(lMin, val), max(rMax, val) // 更新各个返回值
		}
		return false, 0, 0, 0
	}
	dfs(root)
	return sum
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}

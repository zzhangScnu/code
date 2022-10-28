package tree

import (
	. "algorithm.com/structure"
	"math"
)

//给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
//
// 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层
//为第 h 层，则该层包含 1~ 2ʰ 个节点。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3,4,5,6]
//输出：6
//
//
// 示例 2：
//
//
//输入：root = []
//输出：0
//
//
// 示例 3：
//
//
//输入：root = [1]
//输出：1
//
//
//
//
// 提示：
//
//
// 树中节点的数目范围是[0, 5 * 10⁴]
// 0 <= Node.val <= 5 * 10⁴
// 题目数据保证输入的树是 完全二叉树
//
//
//
//
// 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
//
// Related Topics 树 深度优先搜索 二分查找 二叉树

// 时间复杂度是O(logN * logN)，最坏情况下：
// 遍历二叉树高度的次数，即logN
// 在每层的while循环里花费的时间是子树的高度，即logN
func countNodes(root *TreeNode) int {
	if root == nil {
		return 0
	}
	l, r := 0, 0
	left, right := root, root
	for left != nil {
		l++
		left = left.Left
	}
	for right != nil {
		r++
		right = right.Right
	}
	if l == r { // 子树是满二叉树
		return int(math.Pow(float64(2), float64(l))) - 1
	}
	// 子树不是满二叉树，就要用常规遍历二叉树的方式去计算节点个数
	return countNodes(root.Left) + countNodes(root.Right) + 1
}

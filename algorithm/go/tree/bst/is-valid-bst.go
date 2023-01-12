package bst

import . "algorithm.com/structure"

//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
//
// 有效 二叉搜索树定义如下：
//
//
// 节点的左子树只包含 小于 当前节点的数。
// 节点的右子树只包含 大于 当前节点的数。
// 所有左子树和右子树自身必须也是二叉搜索树。
//
//
//
//
// 示例 1：
//
//
//输入：root = [2,1,3]
//输出：true
//
//
// 示例 2：
//
//
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
//
//
//
//
// 提示：
//
//
// 树中节点数目范围在[1, 10⁴] 内
// -2³¹ <= Node.val <= 2³¹ - 1
//
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树

func isValidBST(root *TreeNode) bool {
	return validateBST(root, nil, nil)
}

func validateBST(root *TreeNode, min *TreeNode, max *TreeNode) bool {
	if root == nil {
		return true
	}
	flag := (min == nil || min != nil && min.Val < root.Val) &&
		(max == nil || max != nil && max.Val > root.Val)
	return flag &&
		validateBST(root.Left, min, root) &&
		validateBST(root.Right, root, max)
}

func isValidBST2(root *TreeNode) bool {
	flag, _, _ := validate(root)
	return flag
}

// 返回值：是否二叉搜索树，最小值，最大值
func validate(p *TreeNode) (bool, *TreeNode, *TreeNode) {
	if p == nil {
		return true, nil, nil
	}
	leftFlag, leftMin, leftMax := validate(p.Left)
	rightFlag, rightMin, rightMax := validate(p.Right)
	if !leftFlag || !rightFlag {
		return false, nil, nil
	}
	if leftMax != nil && leftMax.Val >= p.Val {
		return false, nil, nil
	}
	if rightMin != nil && rightMin.Val <= p.Val {
		return false, nil, nil
	}
	// 当p是叶子节点时
	if leftMin == nil {
		leftMin = p
	}
	if rightMax == nil {
		rightMax = p
	}
	return true, leftMin, rightMax
}

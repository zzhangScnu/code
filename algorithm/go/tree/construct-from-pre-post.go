package tree

import . "algorithm.com/structure"

//给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵
//树的后序遍历，重构并返回二叉树。
//
// 如果存在多个答案，您可以返回其中 任何 一个。
//
//
//
// 示例 1：
//
//
//
//
//输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
//输出：[1,2,3,4,5,6,7]
//
//
// 示例 2:
//
//
//输入: preorder = [1], postorder = [1]
//输出: [1]
//
//
//
//
// 提示：
//
//
// 1 <= preorder.length <= 30
// 1 <= preorder[i] <= preorder.length
// preorder 中所有值都 不同
// postorder.length == preorder.length
// 1 <= postorder[i] <= postorder.length
// postorder 中所有值都 不同
// 保证 preorder 和 postorder 是同一棵二叉树的前序遍历和后序遍历
//
//
// Related Topics 树 数组 哈希表 分治 二叉树

var postMapping map[int]int

func constructFromPrePost(preorder []int, postorder []int) *TreeNode {
	postMapping = make(map[int]int, len(postorder))
	for i, val := range postorder {
		postMapping[val] = i
	}
	return doConstructFromPrePost(preorder, 0, len(preorder)-1,
		postorder, 0, len(postorder)-1)
}

func doConstructFromPrePost(preorder []int, preStart int, preEnd int,
	postorder []int, postStart int, postEnd int) *TreeNode {
	if preStart > preEnd || postStart > postEnd {
		return nil
	}
	rootVal := preorder[preStart]
	if preStart == preEnd { // 子树里只剩一个节点需要构建了，即为叶子节点
		return &TreeNode{Val: rootVal}
	}
	leftChildVal := preorder[preStart+1] // 假设preStart+1为左子树的根节点
	leftChildPivot := postMapping[leftChildVal]
	leftTreeSize := leftChildPivot - postStart + 1 // 从后序遍历中确定左子树的大小，从而把序列分为左右子树两部分
	return &TreeNode{
		Val:   rootVal,
		Left:  doConstructFromPrePost(preorder, preStart+1, preStart+leftTreeSize, postorder, postStart, leftChildPivot),
		Right: doConstructFromPrePost(preorder, preStart+leftTreeSize+1, preEnd, postorder, leftChildPivot+1, postEnd-1),
	}
}

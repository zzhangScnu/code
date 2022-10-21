package tree

import . "algorithm.com/structure"

//给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并
//返回这颗 二叉树 。
//
//
//
// 示例 1:
//
//
//输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//输出：[3,9,20,null,null,15,7]
//
//
// 示例 2:
//
//
//输入：inorder = [-1], postorder = [-1]
//输出：[-1]
//
//
//
//
// 提示:
//
//
// 1 <= inorder.length <= 3000
// postorder.length == inorder.length
// -3000 <= inorder[i], postorder[i] <= 3000
// inorder 和 postorder 都由 不同 的值组成
// postorder 中每一个值都在 inorder 中
// inorder 保证是树的中序遍历
// postorder 保证是树的后序遍历
//
//
// Related Topics 树 数组 哈希表 分治 二叉树

var inorderMappings map[int]int

func buildTreeFromInorderPostOrder(inorder []int, postorder []int) *TreeNode {
	inorderMappings = make(map[int]int, len(inorder))
	for i, val := range inorder {
		inorderMappings[val] = i
	}
	return doBuildTreeFromInorderPostOrder(postorder, 0, len(postorder)-1,
		inorder, 0, len(inorder)-1)

}

func doBuildTreeFromInorderPostOrder(postorder []int, postStart int, postEnd int,
	inorder []int, inStart int, inEnd int) *TreeNode {
	if postStart > postEnd {
		return nil
	}
	val := postorder[postEnd]
	inPivot := inorderMappings[val]
	rightInSize := inEnd - inPivot
	return &TreeNode{
		Val:   val,
		Left:  doBuildTreeFromInorderPostOrder(postorder, postStart, postEnd-rightInSize-1, inorder, inStart, inPivot-1),
		Right: doBuildTreeFromInorderPostOrder(postorder, postEnd-rightInSize, postEnd-1, inorder, inPivot+1, inEnd),
	}
}

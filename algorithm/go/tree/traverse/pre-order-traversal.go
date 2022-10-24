package traverse

import (
	. "algorithm.com/structure"
	"container/list"
)

// 只能作为全局变量，传参不好使
var res []int

func traverseRecursively(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	res = []int{}
	doTraverseRecursively(root)
	return res
}

func doTraverseRecursively(node *TreeNode) {
	if node == nil {
		return
	}
	res = append(res, node.Val)
	doTraverseRecursively(node.Left)
	doTraverseRecursively(node.Right)
}

func traverseDp(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	return doTraverseDp(root)
}

func doTraverseDp(node *TreeNode) []int {
	var levelRes []int
	if node == nil {
		return levelRes
	}
	levelRes = append(levelRes, node.Val)
	levelRes = append(levelRes, doTraverseDp(node.Left)...)
	levelRes = append(levelRes, doTraverseDp(node.Right)...)
	return levelRes
}

func traverseByIterator(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	var iRes []int
	stack := list.New()
	stack.PushBack(root)
	for stack.Len() != 0 {
		e := stack.Back()
		stack.Remove(e)
		node := e.Value.(*TreeNode)
		iRes = append(iRes, node.Val)
		if node.Right != nil {
			stack.PushBack(node.Right)
		}
		if node.Left != nil {
			stack.PushBack(node.Left)
		}
	}
	return iRes
}

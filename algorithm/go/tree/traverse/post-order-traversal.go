package traverse

import (
	. "algorithm.com/structure"
	"container/list"
)

var postRes []int

func traversePostOrderRecursively(root *TreeNode) []int {
	doTraversePostOrderRecursively(root)
	return postRes
}

func doTraversePostOrderRecursively(node *TreeNode) {
	if node == nil {
		return
	}
	doTraversePostOrderRecursively(node.Left)
	doTraversePostOrderRecursively(node.Right)
	postRes = append(postRes, node.Val)
}

func traversePostOrderDp(root *TreeNode) []int {
	var levelRes []int
	if root == nil {
		return levelRes
	}
	levelRes = append(levelRes, traversePostOrderDp(root.Left)...)
	levelRes = append(levelRes, traversePostOrderDp(root.Right)...)
	levelRes = append(levelRes, root.Val)
	return levelRes
}

func traversePostOrderByIterator(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	iterateStack := list.New()
	resStack := list.New()
	iterateStack.PushBack(root)
	for iterateStack.Len() != 0 {
		e := iterateStack.Back()
		node := e.Value.(*TreeNode)
		iterateStack.Remove(e)
		resStack.PushBack(node)
		if node.Left != nil {
			iterateStack.PushBack(node.Left)
		}
		if node.Right != nil {
			iterateStack.PushBack(node.Right)
		}
	}
	var pRes []int
	for resStack.Len() != 0 {
		e := resStack.Back()
		node := e.Value.(*TreeNode)
		resStack.Remove(e)
		pRes = append(pRes, node.Val)
	}
	return pRes
}

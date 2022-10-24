package traverse

import (
	. "algorithm.com/structure"
	"container/list"
)

var inRes []int

func traverseInOrderRecursively(root *TreeNode) []int {
	doTraverseInOrderRecursively(root)
	return inRes
}

func doTraverseInOrderRecursively(node *TreeNode) {
	if node == nil {
		return
	}
	doTraverseInOrderRecursively(node.Left)
	inRes = append(inRes, node.Val)
	doTraverseInOrderRecursively(node.Right)
}

func traverseInOrderDp(root *TreeNode) []int {
	var levelRes []int
	if root == nil {
		return levelRes
	}
	levelRes = append(levelRes, traverseInOrderDp(root.Left)...)
	levelRes = append(levelRes, root.Val)
	levelRes = append(levelRes, traverseInOrderDp(root.Right)...)
	return levelRes
}

func traverseInOrderByIterator(root *TreeNode) []int {
	var iRes []int
	stack := list.New()
	cur := root
	for cur != nil || stack.Len() != 0 {
		if cur != nil { // 根节点先不访问，而是入栈；一直往左子树方向深入
			stack.PushBack(cur)
			cur = cur.Left
			continue
		}
		// 当前指针指向的节点已经是空的了，可以从栈里面取出元素来访问了
		e := stack.Back()
		node := e.Value.(*TreeNode)
		iRes = append(iRes, node.Val)
		stack.Remove(e)
		// 将当前指针指向右子树
		cur = node.Right
	}
	return iRes
}

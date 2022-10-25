package traverse

import (
	. "algorithm.com/structure"
	"container/list"
)

var preNaryRes []int

func traverseNaryRecursively(root *Node) []int {
	preNaryRes = []int{}
	doTraverseNaryRecursively(root)
	return preNaryRes
}

func doTraverseNaryRecursively(node *Node) {
	if node == nil {
		return
	}
	preNaryRes = append(preNaryRes, node.Val)
	for _, c := range node.Children {
		doTraverseNaryRecursively(c)
	}
}

func traverseNaryByDp(root *Node) []int {
	if root == nil {
		return []int{}
	}
	var res []int
	res = append(res, root.Val)
	for _, c := range root.Children {
		res = append(res, traverseNaryByDp(c)...)
	}
	return res
}

func traverseNaryByIterator(root *Node) []int {
	if root == nil {
		return []int{}
	}
	var res []int
	stack := list.New()
	stack.PushBack(root)
	for stack.Len() != 0 {
		e := stack.Back()
		stack.Remove(e)
		node := e.Value.(*Node)
		res = append(res, node.Val)
		idx := len(node.Children) - 1
		for idx >= 0 {
			stack.PushBack(node.Children[idx])
			idx--
		}
	}
	return res
}

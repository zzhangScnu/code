package traverse

import (
	. "algorithm.com/structure"
	"container/list"
)

func traverseNaryPostOrderRecursively(root *Node) (res []int) {
	var dfs func(node *Node)
	dfs = func(node *Node) {
		if node == nil {
			return
		}
		for _, c := range node.Children {
			dfs(c) //因为要自调用，所以dfs要提前声明出来
		}
		res = append(res, node.Val)
		return
	}
	dfs(root)
	return
}

func traverseNaryPostOrderByDp(root *Node) (res []int) {
	if root == nil {
		return
	}
	for _, c := range root.Children {
		res = append(res, traverseNaryPostOrderByDp(c)...)
	}
	res = append(res, root.Val)
	return
}

func traverseNaryPostOrderByIterator(root *Node) (res []int) {
	if root == nil {
		return res
	}
	resStack := list.New()
	iterateStack := list.New()
	iterateStack.PushBack(root)
	for iterateStack.Len() != 0 {
		e := iterateStack.Back()
		iterateStack.Remove(e)
		node := e.Value.(*Node)
		resStack.PushBack(node)
		for _, c := range node.Children {
			iterateStack.PushBack(c)
		}
	}
	for resStack.Len() != 0 {
		e := resStack.Back()
		resStack.Remove(e)
		node := e.Value.(*Node)
		res = append(res, node.Val)
	}
	return
}

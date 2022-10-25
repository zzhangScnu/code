package structure

import (
	"container/list"
	"strconv"
)

type Node struct {
	Val      int
	Children []*Node
}

func BuildNaryRoot(values []string) *Node {
	if len(values) == 0 {
		return &Node{}
	}
	dummy := &Node{}
	queue := list.New()
	queue.PushBack(dummy)
	idx := 0
	for queue.Len() != 0 {
		e := queue.Front()
		queue.Remove(e)
		n := e.Value.(*Node)
		for idx < len(values) && values[idx] != "null" {
			val, _ := strconv.Atoi(values[idx])
			child := &Node{Val: val}
			queue.PushBack(child)
			n.Children = append(n.Children, child)
			idx++
		}
		idx++ // 当前是null，需要往前指向下一个值
	}
	return dummy.Children[0]
}

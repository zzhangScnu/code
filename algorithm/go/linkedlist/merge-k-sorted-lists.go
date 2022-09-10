package main

import (
	. "algorithm.com/structure"
	"container/heap"
)

// 吐槽：go对堆的支持也太差劲了吧……
func mergeKSortedLists(lists []*ListNode) *ListNode {
	if len(lists) == 0 {
		return nil
	}
	dummyHead := &ListNode{
		Val:  -1,
		Next: nil,
	}
	p := dummyHead
	ph := newPriorityQueue(lists)
	for ph.Len() != 0 {
		item := ph.Pop().(*Item)
		idx := item.Index
		p.Next = lists[idx]
		p = p.Next
		lists[idx] = lists[idx].Next
		ph.Push(lists[idx].Val)
	}
	return dummyHead.Next
}

func newPriorityQueue(lists []*ListNode) *PriorityQueue {
	pq := make(PriorityQueue, len(lists))
	for i, node := range lists {
		pq[i] = &Item{
			Val:   node.Val,
			Index: i,
		}
	}
	heap.Init(&pq)
	return &pq
}

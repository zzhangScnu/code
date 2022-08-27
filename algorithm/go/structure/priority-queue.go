package structure

type Item struct {
	Val   int
	Index int
}

type PriorityQueue []*Item

func (p PriorityQueue) Less(i, j int) bool {
	return p[i].Val > p[j].Val
}

func (p PriorityQueue) Swap(i, j int) {
	p[i], p[j] = p[j], p[i]
}

func (p *PriorityQueue) Push(x any) {
	item := Item{Val: x.(int), Index: p.Len()} // x.(*Item)
	*p = append(*p, &item)
}

// Pop 看源码实现，这里是要出列最后一个元素（而不是第一个元素），因为第一步就会将索引0和n-1的元素交换位置
func (p *PriorityQueue) Pop() any {
	pv := *p
	l := len(pv)
	item := pv[l-1]
	pv[l-1] = nil // 内存泄露
	*p = pv[:l-1]
	return item
}

func (p PriorityQueue) Len() int {
	return len(p)
}

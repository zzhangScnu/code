package sliding_window

// 先进先出的单调递减队列
type ascendingQueueInterface interface {
	push(num int)
	popFirst(num int)
	getLast() int
	popLast()
	top() int
}

type ascendingQueue struct {
	nums []int
}

func (a *ascendingQueue) push(num int) {
	for len(a.nums) != 0 && a.getLast() < num {
		a.popLast()
	}
	a.nums = append(a.nums, num)
}

func (a *ascendingQueue) popFirst(num int) {
	if len(a.nums) == 0 {
		return
	}
	if a.nums[0] != num {
		return
	}
	if len(a.nums) == 1 {
		a.nums = nil
		return
	}
	a.nums = a.nums[1:]
}

func (a *ascendingQueue) getLast() int {
	if len(a.nums) == 0 {
		return -1
	}
	return a.nums[len(a.nums)-1]
}

func (a *ascendingQueue) popLast() {
	if len(a.nums) == 0 {
		return
	}
	if len(a.nums) == 1 {
		a.nums = nil
		return
	}
	a.nums = a.nums[0 : len(a.nums)-1]
}

func (a *ascendingQueue) top() int {
	if len(a.nums) == 0 {
		return -1
	}
	return a.nums[0]
}

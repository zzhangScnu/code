package binary_search

func firstBadVersion(n int) int {
	low, high, mid := 1, n, 0
	for low < high { // 因为一定会找到，而取等时的跳出条件是low > high，不可能到达这个条件，会死循环
		mid = low + (high-low)>>1
		if isBadVersion(mid) {
			high = mid
		} else {
			low = mid + 1
		}
	}
	return low
}

func isBadVersion(version int) bool {
	if version < 4 {
		return false
	}
	return true
}

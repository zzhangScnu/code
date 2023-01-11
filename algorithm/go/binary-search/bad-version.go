package binary_search

func firstBadVersion(n int) int {
	low, high, mid := 1, n, 0
	for low <= high {
		mid = low + (high-low)>>1
		if isBadVersion(mid) {
			badVersionIdx := mid
			shrinkMid := low + (badVersionIdx-low)>>1
			if isBadVersion(shrinkMid) {
				badVersionIdx = shrinkMid
			}
			for badVersionIdx >= 0 {
				if isBadVersion(badVersionIdx); badVersionIdx == 0 || !isBadVersion(badVersionIdx-1) {
					return badVersionIdx
				}
				badVersionIdx--
			}
		} else {
			low = mid + (high-mid)>>1
		}
	}
	return -1
}

func isBadVersion(version int) bool {
	if version < 4 {
		return false
	}
	return true
}

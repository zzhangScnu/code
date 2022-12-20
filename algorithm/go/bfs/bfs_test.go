package bfs

import (
	. "algorithm.com/structure"
	"fmt"
	"testing"
)

func TestE(t *testing.T) {
	fmt.Println(1e4)
}

func TestOpenLock(t *testing.T) {
	Validate(openLock([]string{"0201", "0101", "0102", "1212", "2002"}, "0202"), 6, t)
}

package bst

import (
	. "algorithm.com/structure"
	"fmt"
	"testing"
)

func TestFindMode(t *testing.T) {
	root := BuildRoot([]string{"1", "null", "2"})
	res := findMode(root)
	fmt.Println(res)
}

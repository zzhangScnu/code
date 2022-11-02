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

func TestGetMinimumDifference(t *testing.T) {
	root := BuildRoot([]string{"1", "null", "3", "null", "null", "2", "null"})
	res := getMinimumDifference(root)
	Validate(res, 1, t)
}

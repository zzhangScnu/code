package tree

import (
	. "algorithm.com/structure"
	"fmt"
	"testing"
)

func TestBuildRoot(t *testing.T) {
	root := BuildRoot([]string{"3", "9", "20", "null", "null", "15", "7"})
	fmt.Println(root)
}

func TestLevelOrder(t *testing.T) {
	//	root := BuildRoot([]string{"3", "9", "20", "null", "null", "15", "7"})
	//	res := levelOrder(root)
	root := BuildRoot([]string{"1", "2", "3", "4", "5"})
	res := levelOrder(root)
	fmt.Println(res)
}

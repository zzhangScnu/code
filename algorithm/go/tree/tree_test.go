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

func TestZigzagLevelOrder(t *testing.T) {
	root := BuildRoot([]string{"3", "9", "20", "null", "null", "15", "7"})
	res := zigzagLevelOrder(root) // [[3],[20,9],[15,7]]
	fmt.Println(res)
}

func TestMaxDepth(t *testing.T) {
	root := BuildRoot([]string{"1", "2", "3", "4", "5"})
	res := maxDepth(root)
	Validate(res, 3, t)
}

func TestDiameterOfBinaryTree(t *testing.T) {
	root := BuildRoot([]string{"1", "2", "3", "4", "5"})
	res := diameterOfBinaryTree(root)
	Validate(res, 3, t)
	//root := BuildRoot([]string{"1", "2"})
	//res := diameterOfBinaryTree(root)
	//Validate(res, 1, t)
}

func TestConstructTreeFromInorderPostOrder(t *testing.T) {
	root1 := buildTree([]int{3, 9, 20, 15, 7}, []int{9, 3, 15, 20, 7})
	root2 := buildTree([]int{-1}, []int{-1})
	fmt.Println(root1, root2)
}

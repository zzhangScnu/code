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

func TestMaxDepth(t *testing.T) {
	root := BuildRoot([]string{"1", "2", "3", "4", "5"})
	res := maxDepth(root)
	Validate(res, 3, t)
}

func TestMaxDepthNary(t *testing.T) {
	root := BuildNaryRoot([]string{"1", "null", "3", "2", "4", "null", "5", "6"})
	res := maxDepthNary(root)
	Validate(res, 3, t)
	resByTraversal := maxDepthNaryByTraversal(root)
	Validate(resByTraversal, 3, t)
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

func TestBuildTreeFromInorderPostOrder(t *testing.T) {
	root1 := buildTreeFromInorderPostOrder([]int{9, 3, 15, 20, 7}, []int{9, 15, 7, 20, 3})
	root2 := buildTreeFromInorderPostOrder([]int{-1}, []int{-1})
	fmt.Println(root1, root2)
}

func TestConstructFromPrePost(t *testing.T) {
	root := constructFromPrePost([]int{1, 2, 4, 5, 3, 6, 7}, []int{4, 5, 2, 6, 7, 3, 1})
	fmt.Println(root)
}

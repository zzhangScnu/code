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

func TestMinDepth(t *testing.T) {
	root := BuildRoot([]string{"1", "2", "3", "4", "5"})
	res := minDepth(root)
	Validate(res, 2, t)
	resByLevelOrder := minDepthByLevelOrder(root)
	Validate(resByLevelOrder, 2, t)
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

func TestFlatten(t *testing.T) {
	root := BuildRoot([]string{"1", "2", "5", "3", "4", "null", "6"})
	flatten(root)
	fmt.Println(root)
	root2 := BuildRoot([]string{"1", "null", "2"})
	p := root2
	for p.Right != nil {
		p = p.Right
	}
	p.Left = &TreeNode{Val: 3}
	flatten(root2)
	fmt.Println(root2)
}

func TestConnect(t *testing.T) {
	//root := BuildNextTreeRoot([]string{"1", "2", "3", "4", "5", "6", "7"})
	//res := connectByLevelOrderTraversal(root)
	//fmt.Println(res)
	root2 := BuildNextTreeRoot([]string{"1", "2", "3", "4", "5", "6", "7"})
	res2 := connect(root2)
	fmt.Println(res2)
}

func TestInvertTree(t *testing.T) {
	root := BuildRoot([]string{"2", "1", "3"})
	res := invertTree(root)
	fmt.Println(res)
}

func TestCountNodes(t *testing.T) {
	root := BuildRoot([]string{"1", "2", "3", "4", "5", "6"})
	res := countNodes(root)
	Validate(res, 6, t)
}

func TestSerializeAndDeserialize(t *testing.T) {
	str := "1,2,3,null,null,4,5,6,7,null,null,null,null,null,null"
	c := Constructor()
	root := c.deserialize(str)
	res := c.serialize(root)
	Validate(res, str, t)
}

func TestSerializeAndDeserializeRecursively(t *testing.T) {
	str := "1,2,null,4,null,null,3,null,null"
	c := ConstructorRecursively()
	root := c.deserializeRecursively(str)
	res := c.serializeRecursively(root)
	Validate(res, str, t)
}

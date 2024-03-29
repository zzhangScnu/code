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

func TestSerializeAndDeserializePostOrderRecursively(t *testing.T) {
	str := "null,null,null,4,2,null,null,3,1"
	c := ConstructorPostOrderRecursively()
	root := c.deserializePostOrderRecursively(str)
	res := c.serializePostOrderRecursively(root)
	Validate(res, str, t)
}

func TestFindDuplicateSubtrees(t *testing.T) {
	str := "1,2,3,4,null,2,4,null,null,4,null,null,null" // "0,0,0,0,null,null,0,0,0,0,0"
	c := Constructor()
	root := c.deserialize(str)
	subtrees := findDuplicateSubtrees(root)
	Validate(len(subtrees), 2, t)
}

func TestDFS(t *testing.T) {
	root := BuildNaryRoot([]string{"1", "null", "3", "2", "4", "null", "5", "6"})
	traverse(root)
}

func TestBacktrack(t *testing.T) {
	root := BuildNaryRoot([]string{"1", "null", "3", "2", "4", "null", "5", "6"})
	backtrack(root)
}

// DFS 算法，关注点在节点
func traverse(root *Node) {
	if root == nil {
		return
	}
	fmt.Printf("进入节点 %+v\n", root)
	for _, child := range root.Children {
		// 若是在for里面做动作，会漏掉父节点
		// fmt.Printf("进入节点 %+v\n", child)
		traverse(child)
		// fmt.Printf("离开节点 %+v\n", child)
	}
	fmt.Printf("离开节点 %+v\n", root)
}

// 回溯算法，关注点在树枝
func backtrack(root *Node) {
	if root == nil {
		return
	}
	// 关键在于，在本层向前（子树）能做的选择。而向后（父节点）的树枝已经在身后了，是历史做过的选择，所以不需要遍历到
	for _, child := range root.Children {
		// 做选择
		fmt.Printf("从 %+v 到 %+v\n", root, child)
		backtrack(child)
		// 撤销选择
		fmt.Printf("从 %+v 到 %+v\n", child, root)
	}
}

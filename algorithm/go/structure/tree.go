package structure

import "strconv"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func BuildRoot(values []string) *TreeNode {
	return doBuild(0, values)
}

func doBuild(idx int, values []string) *TreeNode {
	if idx >= len(values) {
		return nil
	}
	l := doBuild(idx*2+1, values)
	r := doBuild(idx*2+2, values)
	valStr := values[idx]
	if valStr == "null" {
		return nil
	}
	val, _ := strconv.Atoi(valStr)
	return &TreeNode{
		Val:   val,
		Left:  l,
		Right: r,
	}
}

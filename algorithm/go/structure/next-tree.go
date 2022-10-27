package structure

import "strconv"

type NextNode struct { //Node
	Val   int
	Left  *NextNode
	Right *NextNode
	Next  *NextNode
}

func BuildNextTreeRoot(values []string) *NextNode {
	return doBuildNextTreeRoot(0, values)
}

func doBuildNextTreeRoot(idx int, values []string) *NextNode {
	if idx >= len(values) {
		return nil
	}
	l := doBuildNextTreeRoot(idx*2+1, values)
	r := doBuildNextTreeRoot(idx*2+2, values)
	valStr := values[idx]
	if valStr == "null" {
		return nil
	}
	val, _ := strconv.Atoi(valStr)
	return &NextNode{
		Val:   val,
		Left:  l,
		Right: r,
	}
}

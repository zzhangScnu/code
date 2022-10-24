package traverse

import (
	. "algorithm.com/structure"
	"testing"
)

func TestPreOrderTraversal(t *testing.T) {
	root := BuildRoot([]string{"3", "9", "20", "null", "null", "15", "7"})
	expected := []int{3, 9, 20, 15, 7}
	ValidateDeep(traverseRecursively(root), expected, t)
	ValidateDeep(traverseDp(root), expected, t)
	ValidateDeep(traverseByIterator(root), expected, t)
}

func TestInOrderTraversal(t *testing.T) {
	root := BuildRoot([]string{"3", "9", "20", "null", "null", "15", "7"})
	expected := []int{9, 3, 15, 20, 7}
	ValidateDeep(traverseInOrderRecursively(root), expected, t)
	ValidateDeep(traverseInOrderDp(root), expected, t)
	ValidateDeep(traverseInOrderByIterator(root), expected, t)
}

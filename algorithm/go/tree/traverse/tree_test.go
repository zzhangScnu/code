package traverse

import (
	. "algorithm.com/structure"
	"fmt"
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

func TestPostOrderTraversal(t *testing.T) {
	root := BuildRoot([]string{"3", "9", "20", "null", "null", "15", "7"})
	expected := []int{9, 15, 7, 20, 3}
	ValidateDeep(traversePostOrderRecursively(root), expected, t)
	ValidateDeep(traversePostOrderDp(root), expected, t)
	ValidateDeep(traversePostOrderByIterator(root), expected, t)
}

func TestBuildNaryTree(t *testing.T) {
	root := BuildNaryRoot([]string{"1", "null", "3", "2", "4", "null", "5", "6"})
	fmt.Println(root)
}

func TestPreOrderTraversalNary(t *testing.T) {
	root := BuildNaryRoot([]string{"1", "null", "3", "2", "4", "null", "5", "6"})
	expected := []int{1, 3, 5, 6, 2, 4}
	ValidateDeep(traverseNaryRecursively(root), expected, t)
	ValidateDeep(traverseNaryByDp(root), expected, t)
	ValidateDeep(traverseNaryByIterator(root), expected, t)
}

func TestPostOrderTraversalNary(t *testing.T) {
	root := BuildNaryRoot([]string{"1", "null", "3", "2", "4", "null", "5", "6"})
	expected := []int{5, 6, 3, 2, 4, 1}
	ValidateDeep(traverseNaryPostOrderRecursively(root), expected, t)
	ValidateDeep(traverseNaryPostOrderByDp(root), expected, t)
	ValidateDeep(traverseNaryPostOrderByIterator(root), expected, t)
}

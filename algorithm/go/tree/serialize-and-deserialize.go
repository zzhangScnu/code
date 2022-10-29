package tree

import (
	. "algorithm.com/structure"
	"container/list"
	"strconv"
	"strings"
)

//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [1]
//输出：[1]
//
//
// 示例 4：
//
//
//输入：root = [1,2]
//输出：[1,2]
//
//
//
//
// 提示：
//
//
// 树中结点数在范围 [0, 10⁴] 内
// -1000 <= Node.val <= 1000
//
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树

type Codec struct {
}

func Constructor() Codec {
	return Codec{}
}

// Serializes a tree to a single string.
func (this *Codec) serialize(root *TreeNode) string {
	res := ""
	if root == nil {
		return res
	}
	stack := list.New()
	stack.PushBack(root)
	for stack.Len() != 0 {
		levelSize := stack.Len()
		for i := 0; i < levelSize; i++ {
			e := stack.Front()
			stack.Remove(e)
			node := e.Value.(*TreeNode)
			if node == nil {
				res = res + "null,"
				continue
			}
			res = res + strconv.Itoa(node.Val) + ","
			stack.PushBack(node.Left)
			stack.PushBack(node.Right)
		}
	}
	return res[:len(res)-1]
}

// Deserializes your encoded data to tree.
func (this *Codec) deserialize(data string) *TreeNode {
	if data == "" {
		return nil
	}
	values := strings.Split(data, ",")
	root := parseNode(0, values)
	stack := list.New()
	stack.PushBack(root)
	idx := 1
	for stack.Len() != 0 {
		e := stack.Front()
		stack.Remove(e)
		node := e.Value.(*TreeNode)
		left := parseNode(idx, values)
		idx++
		right := parseNode(idx, values)
		idx++
		node.Left = left
		node.Right = right
		if left != nil {
			stack.PushBack(left)
		}
		if right != nil {
			stack.PushBack(right)
		}
	}
	return root
}

func parseNode(idx int, values []string) *TreeNode {
	if idx >= len(values) {
		return nil
	}
	valStr := values[idx]
	if valStr == "null" {
		return nil
	}
	val, _ := strconv.Atoi(valStr)
	return &TreeNode{Val: val}
}

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

// CodecRecursively 先序和后序都可以，而且因为记录了空节点的位置，所以凭一个先序或后序的结果就可以做到反序列化
// 中序因为无法确定根节点的位置，实现不了
type CodecRecursively struct {
	globalValues []string
}

func ConstructorRecursively() CodecRecursively {
	return CodecRecursively{}
}

func (this *CodecRecursively) serializeRecursively(root *TreeNode) string {
	res := ""
	var dfs func(node *TreeNode)
	dfs = func(node *TreeNode) {
		if node == nil {
			res = res + "null,"
			return
		}
		res = res + strconv.Itoa(node.Val) + ","
		dfs(node.Left)
		dfs(node.Right)
	}
	dfs(root)
	return res[:len(res)-1]
}

// Deserializes your encoded data to tree.
func (this *CodecRecursively) deserializeRecursively(data string) *TreeNode {
	values := strings.Split(data, ",")
	this.globalValues = values
	return this.doDeserializeRecursively()
}

func (this *CodecRecursively) doDeserializeRecursively() *TreeNode {
	if len(this.globalValues) == 0 {
		return nil
	}
	first := this.globalValues[0]
	this.globalValues = this.globalValues[1:] // 无论是不是null，都要去掉已处理的值
	if first == "null" {
		return nil
	}
	val, _ := strconv.Atoi(first)
	node := &TreeNode{Val: val}
	node.Left = this.doDeserializeRecursively()
	node.Right = this.doDeserializeRecursively()
	return node
}

type CodecPostOrderRecursively struct {
	globalValues []string
}

func ConstructorPostOrderRecursively() CodecRecursively {
	return CodecRecursively{}
}

func (this *CodecRecursively) serializePostOrderRecursively(root *TreeNode) string {
	res := ""
	var dfs func(node *TreeNode)
	dfs = func(node *TreeNode) {
		if node == nil {
			res = res + "null,"
			return
		}
		dfs(node.Left)
		dfs(node.Right)
		res = res + strconv.Itoa(node.Val) + ","
	}
	dfs(root)
	return res[:len(res)-1]
}

// Deserializes your encoded data to tree.
func (this *CodecRecursively) deserializePostOrderRecursively(data string) *TreeNode {
	values := strings.Split(data, ",")
	this.globalValues = values
	return this.doDeserializePostOrderRecursively()
}

func (this *CodecRecursively) doDeserializePostOrderRecursively() *TreeNode {
	if len(this.globalValues) == 0 {
		return nil
	}
	lastIdx := len(this.globalValues) - 1
	last := this.globalValues[lastIdx]
	this.globalValues = this.globalValues[:lastIdx] // 无论是不是null，都要去掉已处理的值
	if last == "null" {
		return nil
	}
	val, _ := strconv.Atoi(last)
	node := &TreeNode{Val: val}
	// 遍历顺序是从后往前的，所以要先构造右子树
	node.Right = this.doDeserializePostOrderRecursively()
	node.Left = this.doDeserializePostOrderRecursively()
	return node
}

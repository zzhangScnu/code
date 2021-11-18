package tree;

// 297-二叉树的序列化与反序列化
// serialize-and-deserialize-binary-tree
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
// 树中结点数在范围 [0, 104] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树
// 👍 681 👎 0

import structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lihua
 * @since 2021/11/18
 */
public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> resultList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current == null) {
                resultList.add("null");
            } else {
                resultList.add(String.valueOf(current.val));
                queue.add(current.left);
                queue.add(current.right);
            }
        }
        return String.join(",", resultList);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] elements = data.split(",");
        if ("null".equals(elements[0])) {
            return null;
        }
        int length = elements.length;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(elements[0]));
        queue.add(root);
        int index = 1;
        TreeNode current;
        while (!queue.isEmpty() && index < length) {
            current = queue.poll();
            if (!"null".equals(elements[index])) {
                current.left = new TreeNode(Integer.parseInt(elements[index]));
                queue.add(current.left);
            }
            index++;
            if (!"null".equals(elements[index])) {
                current.right = new TreeNode(Integer.parseInt(elements[index]));
                queue.add(current.right);
            }
            index++;
        }
        return root;
    }

    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree clazz = new SerializeAndDeserializeBinaryTree();
        TreeNode treeNode = clazz.deserialize("1,2,3,null,null,4,5");
        assert treeNode != null;
        String serialized = clazz.serialize(treeNode);
        assert serialized != null;
    }
}

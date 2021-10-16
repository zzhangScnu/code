import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
//
// 叶子节点 是指没有子节点的节点。
//
//
// 示例 1：
//
//
//输入：root = [1,2,3,null,5]
//输出：["1->2->5","1->3"]
//
//
// 示例 2：
//
//
//输入：root = [1]
//输出：["1"]
//
//
//
//
// 提示：
//
//
// 树中节点的数目在范围 [1, 100] 内
// -100 <= Node.val <= 100

/**
 * @author lihua
 * @since 2021/10/16
 */
public class BinaryTreePaths {

    private Deque<Integer> pathList = new ArrayDeque<>();

    private List<Deque<Integer>> resultList = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        doBinaryTreePaths(root);
        return convert(resultList);
    }

    private void doBinaryTreePaths(TreeNode p) {
        if (Objects.isNull(p)) {
            return;
        }
        // 记录一下路径
        pathList.offerLast(p.val);
        if (Objects.isNull(p.left) && Objects.isNull(p.right)) {
            // 避免重复引用
            resultList.add(new ArrayDeque<>(pathList));
        }
        doBinaryTreePaths(p.left);
        doBinaryTreePaths(p.right);
        // 运行到这里，表示已经遍历完其中的一条分支。此时回退一层，遍历其他分支
        pathList.pollLast();
    }

    private List<String> convert(List<Deque<Integer>> resultList) {
        return resultList.stream()
                .filter(pathList -> !pathList.isEmpty())
                .map(pathList -> pathList.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining("->")))
                .collect(Collectors.toList());
    }

    public static class TreeNode {

        int val;

        TreeNode left;

        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

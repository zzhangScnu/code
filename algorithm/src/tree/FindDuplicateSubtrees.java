package tree;

// 652-寻找重复的子树
// find-duplicate-subtrees
//给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
//
// 两棵树重复是指它们具有相同的结构以及相同的结点值。
//
// 示例 1：
//
//         1
//       / \
//      2   3
//     /   / \
//    4   2   4
//       /
//      4
//
//
// 下面是两个重复的子树：
//
//       2
//     /
//    4
//
//
// 和
//
//     4
//
//
// 因此，你需要以列表的形式返回上述重复子树的根结点。
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树

import structure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author lihua
 * @since 2021/11/22
 */
public class FindDuplicateSubtrees {

    private List<TreeNode> resultList = new ArrayList<>();

    private HashMap<String, Integer> counter = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        doFindDuplicateSubtrees(root);
        return resultList;
    }

    private String doFindDuplicateSubtrees(TreeNode p) {
        if (p == null) {
            return "#";
        }
        StringBuilder sb = new StringBuilder();
        String left = doFindDuplicateSubtrees(p.left);
        String right = doFindDuplicateSubtrees(p.right);
        // 这里用先序/后序都行，唯独不能用中序。因为中序序列不能唯一确定一棵树
        String tree = sb.append(left).append(",")
                .append(right).append(",")
                .append(p.val).toString();
        if (counter.containsKey(tree) && counter.get(tree) == 1) {
            resultList.add(p);
        }
        counter.put(tree, counter.getOrDefault(tree, 0) + 1);
        return tree;
    }
}

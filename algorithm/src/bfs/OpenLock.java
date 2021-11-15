package bfs;
// 752-打开转盘锁
// open-the-lock
//你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
// 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
//
// 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
//
// 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
//
// 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
//
//
//
// 示例 1:
//
//
//输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//输出：6
//解释：
//可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
//注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
//因为当拨动到 "0102" 时这个锁就会被锁定。
//
//
// 示例 2:
//
//
//输入: deadends = ["8888"], target = "0009"
//输出：1
//解释：
//把最后一位反向旋转一次即可 "0000" -> "0009"。
//
//
// 示例 3:
//
//
//输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], targ
//et = "8888"
//输出：-1
//解释：
//无法旋转到目标数字且不被锁定。
//
//
// 示例 4:
//
//
//输入: deadends = ["0000"], target = "8888"
//输出：-1
//
//
//
//
// 提示：
//
//
// 1 <= deadends.length <= 500
// deadends[i].length == 4
// target.length == 4
// target 不在 deadends 之中
// target 和 deadends[i] 仅由若干位数字组成
//
// Related Topics 广度优先搜索 数组 哈希表 字符串

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author lihua
 * @since 2021/11/15
 */
public class OpenLock {

    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        // 防止走回头路。如果是单向结构(如树)就不需要担心重复的问题
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int step = 0;
        int levelSize;
        String current;
        queue.add("0000");
        while (!queue.isEmpty()) {
            levelSize = queue.size();
            // 按层序去遍历节点，一层代表一个转盘数字的改变
            for (int i = 0; i < levelSize; i++) {
                current = queue.poll();
                if (dead.contains(current)) {
                    continue;
                }
                if (target.equals(current)) {
                    return step;
                }
                // 对于当前状态，可以拨动四个转盘中的其中一个；可以向上，也可以向下
                for (int j = 0; j < 4; j++) {
                    judgeAndAdd(visited, queue, up(current, j));
                    judgeAndAdd(visited, queue, down(current, j));
                }
            }
            // 只有本层遍历完了，步数才会+1
            step++;
        }
        return -1;
    }

    private void judgeAndAdd(Set<String> visited, Queue<String> queue, String str) {
        if (visited.contains(str)) {
            return;
        }
        visited.add(str);
        queue.offer(str);
    }

    /**
     * 向上拨动，数字是自增的，增加到9就回归到0
     */
    private String up(String current, int index) {
        char[] chars = current.toCharArray();
        if (chars[index] == '9') {
            chars[index] = '0';
        } else {
            chars[index] += 1;
        }
        return new String(chars);
    }

    /**
     * 向下拨动，数字是递减的，减小到0就跳转到9
     */
    private String down(String current, int index) {
        char[] chars = current.toCharArray();
        if (chars[index] == '0') {
            chars[index] = '9';
        } else {
            chars[index] -= 1;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        OpenLock clazz = new OpenLock();
        String[] deadends = new String[]{"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        int result = clazz.openLock(deadends, target);
        assert result == 6;
    }
}

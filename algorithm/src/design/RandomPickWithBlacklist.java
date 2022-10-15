package src.design;

//给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入 黑名单
//blacklist 的整数。任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。
//
// 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
//
// 实现 Solution 类:
//
//
// Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
// int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数
//
//
//
//
// 示例 1：
//
//
//输入
//["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
//[[7, [2, 3, 5]], [], [], [], [], [], [], []]
//输出
//[null, 0, 4, 1, 6, 1, 0, 4]
//
//解释
//Solution solution = new Solution(7, [2, 3, 5]);
//solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，
//                 // 0、1、4和6的返回概率必须相等(即概率为1/4)。
//solution.pick(); // 返回 4
//solution.pick(); // 返回 1
//solution.pick(); // 返回 6
//solution.pick(); // 返回 1
//solution.pick(); // 返回 0
//solution.pick(); // 返回 4
//
//
//
//
// 提示:
//
//
// 1 <= n <= 10⁹
// 0 <= blacklist.length <= min(10⁵, n - 1)
// 0 <= blacklist[i] < n
// blacklist 中所有值都 不同
// pick 最多被调用 2 * 10⁴ 次
//
//
// Related Topics 哈希表 数学 二分查找 排序 随机化

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomPickWithBlacklist {

    static class Solution {

        private HashMap<Integer, Integer> blackMap; // key -> index

        private final int validSize; // 有效元素组成的数组的大小

        private void initBlackMap(int[] blacklist) {
            blackMap = new HashMap<>();
            for (int j : blacklist) {
                blackMap.put(j, j);
            }
        }

        // 把黑名单元素交换到原数组的最后。目的是把原数组分为两部分，前一部分是有效元素，后一部分是黑名单元素。
        // 名为交换，其实是把blackMap里面指向的索引更改一下而已
        private void swapBlackElementToEnd(int n) {
            int cur = n - 1 ; // 等待跟黑名单元素交换，位于原数组末尾
            for (int b : blackMap.keySet()) {
                if (b >= validSize) { // b本来就在原数组末尾，不用管
                    continue;
                }
                while (blackMap.containsKey(cur)) { // cur本来就在黑名单里，不用管
                    cur--;
                }
                blackMap.put(b, cur);
                cur--;
            }
        }

        public Solution(int n, int[] blacklist) {
            validSize = n - blacklist.length;
            initBlackMap(blacklist);
            swapBlackElementToEnd(n);
        }

        public int pick() {
            int val = new Random().nextInt(0, validSize);
            if (blackMap.containsKey(val)) {
                System.out.println(blackMap.get(val));
                return blackMap.get(val);
            }
            System.out.println(val);
            return val;
        }
    }

    public static void main(String[] args) {
        int[] blacklist = new int[]{2, 3, 5};
        Solution solution = new Solution(7, blacklist);
        solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，0、1、4和6的返回概率必须相等(即概率为1/4)。
        solution.pick(); // 返回 4
        solution.pick(); // 返回 1
        solution.pick(); // 返回 6
        solution.pick(); // 返回 1
        solution.pick(); // 返回 0
        solution.pick(); // 返回 4
    }
}

package base;

import java.util.concurrent.ThreadLocalRandom;
// 470-用 Rand7() 实现 Rand10()
// implement-rand10-using-rand7
//给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。
//
// 你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。
//
//
//
//
// 每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
//
//
//
// 示例 1:
//
//
//输入: 1
//输出: [2]
//
//
// 示例 2:
//
//
//输入: 2
//输出: [2,8]
//
//
// 示例 3:
//
//
//输入: 3
//输出: [3,8,10]
//
//
//
//
// 提示:
//
//
// 1 <= n <= 105
//
//
//
//
// 进阶:
//
//
// rand7()调用次数的 期望值 是多少 ?
// 你能否尽量少调用 rand7() ?
//
// Related Topics 数学 拒绝采样 概率与统计 随机化

// The rand7() API is already defined in the parent class SolBase.
// public int rand7();
// @return a random integer in the range 1 to 7

/**
 * @author lihua
 * @since 2022/3/22
 */
public class Rand10 {

    /**
     * 题目给的rand7()，范围是1-7
     */
    public int rand7() {
        return ThreadLocalRandom.current().nextInt(1, 8);
    }

    /**
     * 自定义的rand7()，范围是0-6
     */
    public int customizedRand7() {
        return rand7() - 1;
    }

    /**
     * [1-7][1-7] ~> [1-10]
     */
    public int rand10() {
        int left;
        int right;
        int result;
        do {
            left = customizedRand7();
            right = customizedRand7();
            // result的范围是0-48
            result = left * 7 + right;
            // 只取0-39这40个数。因为剩下的数凑不成10个数来做映射，所以全部舍掉
        } while (result >= 40);
        // result / 4 的范围是0-9，再加1变成1-10
        return result / 4 + 1;
    }

    public static void main(String[] args) {
        Rand10 rand10 = new Rand10();
        for (int i = 0; i < 100; i++) {
            System.out.println(rand10.rand10());
        }
    }
}

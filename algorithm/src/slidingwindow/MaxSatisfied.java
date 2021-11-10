package slidingwindow;
// 1052-爱生气的书店老板
// grumpy-bookstore-owner
//今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分
//钟结束后离开。
//
// 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一
//分钟的顾客就会不满意，不生气则他们是满意的。
//
// 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
//
// 请你返回这一天营业下来，最多有多少客户能够感到满意。
//
//
// 示例：
//
//
//输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
//输出：16
//解释：
//书店老板在最后 3 分钟保持冷静。
//感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
//
//
//
//
// 提示：
//
//
// 1 <= X <= customers.length == grumpy.length <= 20000
// 0 <= customers[i] <= 1000
// 0 <= grumpy[i] <= 1
//
// Related Topics 数组 滑动窗口

/**
 * 这题跟最大连续1的个数III不一样的地方在于：
 * 最大连续1的个数III：是最多可以翻转K个数字，相当于最多可以增加K个符合题意的元素，K的名额不考虑原有的元素，所以区间大小不固定；
 * 本题：minutes要考虑原来的元素，等于固定区间为minutes大小
 *
 * @author lihua
 * @since 2021/11/10
 */
public class MaxSatisfied {

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int length = customers.length;
        int left = 0, right = 0, maxLeft = 0;
        int satisfied = 0, maxSatisfied = 0;
        while (right < length) {
            // 如果此时生气，会影响的用户数量
            if (grumpy[right] == 1) {
                satisfied += customers[right];
            }
            // 当影响的用户数量越多，此时强行不生气带来的收益就越大
            if (satisfied > maxSatisfied) {
                maxSatisfied = satisfied;
                // 更新发动技能时的左界
                maxLeft = left;
            }
            right++;
            // 区间固定minutes
            while (right - left + 1 > minutes) {
                if (grumpy[left] == 1) {
                    satisfied -= customers[left];
                }
                left++;
            }
        }
        // 根据左界，改变老板不生气的区间
        for (int i = maxLeft; i < maxLeft + minutes; i++) {
            grumpy[i] = 0;
        }
        // 计算结果
        int result = 0;
        for (int i = 0; i < length; i++) {
            if (grumpy[i] == 0) {
                result += customers[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MaxSatisfied clazz = new MaxSatisfied();
        int[] customers = new int[]{1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = new int[]{0, 1, 0, 1, 0, 1, 0, 1};
        int minutes = 3;
        int result = clazz.maxSatisfied(customers, grumpy, minutes);
        assert result == 16;
    }
}

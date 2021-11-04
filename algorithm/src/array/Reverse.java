package array;
//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
//
//
// 示例 1：
//
//
//输入：x = 123
//输出：321
//
//
// 示例 2：
//
//
//输入：x = -123
//输出：-321
//
//
// 示例 3：
//
//
//输入：x = 120
//输出：21
//
//
// 示例 4：
//
//
//输入：x = 0
//输出：0
//
//
//
//
// 提示：
//
//
// -231 <= x <= 231 - 1
//
// Related Topics 数学

/**
 * @author lihua
 * @since 2021/11/4
 */
public class Reverse {

    public int reverse(int x) {
        int result = 0;
        int remainder;
        // != 0 的判断，兼容了正负数
        while (x != 0) {
            remainder = x % 10;
            // 因为最长限制只有32位，而32位最长为2147483647，所以只能从前31位数字判断，否则某一轮 * 10，结果就要溢出了
            // 1. 如果前31位已经超出了31位最大值，那肯定溢出了
            // 2. 如果前31位没有超，就判断末位的数字，有没有超过2147483647的最后一位7
            if (result > 214748364 || (result == 214748364 && remainder > 7)) {
                return 0;
            }
            if (result < -214748364 || (result == -214748364 && remainder < -8)) {
                return 0;
            }
            x = x / 10;
            result = result * 10 + remainder;
        }
        return result;
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        int result = reverse.reverse(-123);
        assert result == -321;
    }
}

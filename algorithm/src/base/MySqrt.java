package base;

// 69-Sqrt(x)
// sqrtx
//给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
//
// 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
//
// 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
//
//
//
// 示例 1：
//
//
//输入：x = 4
//输出：2
//
//
// 示例 2：
//
//
//输入：x = 8
//输出：2
//解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
//
//
//
//
// 提示：
//
//
// 0 <= x <= 231 - 1
//
// Related Topics 数学 二分查找

/**
 * @author lihua
 * @since 2022/1/28
 */
public class MySqrt {

    public int mySqrt(int x) {
        int low = 0;
        int high = x;
        int mid;
        int result = 0;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            // 如果操作数不转换成long的话，会发生上溢
            // 先将一个操作数转换成long，左边的运算结果会是long类型；在与右边进行比较时，右边会隐式向上转换为long
            if ((long) mid * mid <= x) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MySqrt clazz = new MySqrt();
        int result = clazz.mySqrt(2147395599);
        assert result == 46339;
    }
}

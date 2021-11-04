package array;
//给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
//
// 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
//
//
//
// 示例 1：
//
//
//输入：x = 121
//输出：true
//
//
// 示例 2：
//
//
//输入：x = -121
//输出：false
//解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
//
//
// 示例 3：
//
//
//输入：x = 10
//输出：false
//解释：从右向左读, 为 01 。因此它不是一个回文数。
//
//
// 示例 4：
//
//
//输入：x = -101
//输出：false
//
//
//
//
// 提示：
//
//
// -231 <= x <= 231 - 1
//
//
//
//
// 进阶：你能不将整数转为字符串来解决这个问题吗？
// Related Topics 数学

/**
 * @author lihua
 * @since 2021/11/4
 */
public class IsPalindrome {

    public boolean isPalindrome(int x) {
        // 如果x是负数，那么肯定不是回文数
        if (x < 0) {
            return false;
        }
        // 如果x的末尾是0，那么翻转之后首位为0，满足回文数要求的只有0
        if (x % 10 == 0) {
            return x == 0;
        }
        // halfRevert是x的后半部分翻转的结果。如果x是回文数：
        // 1. x位数是偶数：x和halfRevert会相等
        // 2. x位数是奇数：因为结束条件是halfRevert >= x，所以最后halfRevert会带有x最中间的一位，此时需要去掉再判等
        int halfRevert = 0;
        while (halfRevert < x) {
            halfRevert = halfRevert * 10 + x % 10;
            x = x / 10;
        }
        //
        return halfRevert == x || halfRevert / 10 == x;
    }

    public static void main(String[] args) {
        IsPalindrome palindrome = new IsPalindrome();
        boolean result = palindrome.isPalindrome(121);
        assert result;
    }
}

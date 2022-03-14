package base;

import java.util.Scanner;
import java.util.Stack;

/**
 * HW-OD
 *
 * 给编码过的压缩字符串解码，形如 n[str]
 * n是数字，str是字符串。代表str重复n遍
 * 注意会有嵌套情况，形如 3[k2[m]]
 *
 * @author lihua
 * @since 2022/3/13
 */
public class DecodeDuplication {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stack<String> stack = new Stack<>();
        String str = in.next();
        int length = str.length();
        String cur;
        for (int i = 0; i < length; i++) {
            cur = str.substring(i, i + 1);
            // n是多位数字的时候，容易遗漏
            while (cur.matches("[0-9]+") && str.substring(i + 1, i + 2).matches("[0-9]+")) {
                cur += str.substring(i + 1, i + 2);
                i++;
            }
            if ("[".equals(cur)) {
                continue;
            }
            if (!"]".equals(cur)) {
                stack.push(cur);
            } else {
                calculate(stack);
            }
        }
        while (stack.size() > 1) {
            calculate(stack);
        }
        System.out.println(stack.pop());
    }

    private static void calculate(Stack<String> stack) {
        // 字符串
        String first = stack.pop();
        // 可能是字符串，也可能是数字
        String second = stack.pop();
        if (second.matches("[a-z]+")) {
            stack.push(second + first);
            return;
        }
        int num = Integer.parseInt(second);
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < num; j++) {
            sb.append(first);
        }
        stack.push(sb.toString());
    }
}

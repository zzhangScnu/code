package base;

/**
 * HW-OD
 * 实现简单剪切板
 * 1 - 输入a
 * 2 - ctrl + c
 * 3 - ctrl + x
 * 4 - ctrl - v
 * 5 - ctrl - a
 * 2和3如果在没有选中的情况下用，是没有效果的
 * 输入一串指令，最后会剩多少个字符？
 *
 * @author lihua
 * @since 2022/3/13
 */
public class Editor {

    public static int process(String arg) {
        String[] inputArr = arg.split(" ");
        int result = 0;
        // 缓冲区，相当于剪贴板
        int temp = 0;
        boolean choose = false;
        for (String ch : inputArr) {
            switch (ch) {
                case "1":
                    if (!choose) {
                        result += 1;
                    } else {
                        result = 1;
                        choose = false;
                    }
                    break;
                case "5":
                    choose = true;
                    break;
                case "2":
                    if (choose) {
                        temp = result;
                    }
                    break;
                case "3":
                    if (choose) {
                        temp = result;
                        result = 0;
                    }
                    break;
                case "4":
                    if (!choose) {
                        result += temp;
                    } else {
                        result = temp;
                        choose = false;
                    }
                default:
            }
        }
        return result;
    }
}

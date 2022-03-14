package sorting;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * HW-OD
 * 按身高绝对值大小，升序排序
 * 如果绝对值相同，则矮的排前面
 *
 * @author lihua
 * @since 2022/3/13
 */
public class SortByHeight {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int height = in.nextInt();
        int num = in.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>(num, (x, y) -> {
            int length1 = Math.abs(x - height);
            int length2 = Math.abs(y - height);
            return length1 == length2 ? x - y : length1 - length2;
        });
        while (in.hasNextInt()) {
            queue.add(in.nextInt());
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.remove()).append(" ");
        }
        System.out.println(sb);
    }
}
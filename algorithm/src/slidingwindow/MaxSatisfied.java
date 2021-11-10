package slidingwindow;

/**
 * @author lihua
 * @since 2021/11/10
 */
public class MaxSatisfied {

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int length = customers.length;
        int left = 0, right = 0;
        int satisfied = 0, maxSatisfied = 0;
        int[] grumpyCount = new int[]{0, 0};
        while (right < length) {
            satisfied += customers[right];
            grumpyCount[grumpy[right]]++;
            right++;
            while (grumpyCount[0] > minutes) {
                satisfied -= customers[left];
                grumpyCount[grumpy[left]]--;
                left++;
            }
            maxSatisfied = Math.max(satisfied, maxSatisfied);
        }
        return maxSatisfied;
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

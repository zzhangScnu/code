package sorting.base;

import java.util.Arrays;

/**
 * @author lihua
 * @since 2022/1/10
 */
public class InsertionSort {

    public int[] sortArray(int[] nums) {
        doSort(nums);
        return nums;
    }

    private void doSort(int[] nums) {
        int length = nums.length;
        for (int j = 0; j < length; j++) {
            int currentValue = nums[j];
            int i = j - 1;
            while (i >= 0 && currentValue < nums[i]) {
                nums[i + 1] = nums[i];
                i--;
            }
            nums[i + 1] = currentValue;
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        InsertionSort clazz = new InsertionSort();
        clazz.sortArray(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

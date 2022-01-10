package sorting.base;

import java.util.Arrays;

/**
 * @author lihua
 * @since 2022/1/10
 */
public class BubbleSort {

    public int[] sortArray(int[] nums) {
        doSort(nums);
        return nums;
    }

    private void doSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (nums[i] < nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        BubbleSort clazz = new BubbleSort();
        clazz.sortArray(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

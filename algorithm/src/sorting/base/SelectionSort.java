package sorting.base;

import java.util.Arrays;

/**
 * @author lihua
 * @since 2022/1/10
 */
public class SelectionSort {

    public int[] sortArray(int[] nums) {
        doSort(nums);
        return nums;
    }

    private void doSort(int[] nums) {
        int length = nums.length;
        int minIndex;
        for (int placedIndex = 0; placedIndex < length; placedIndex++) {
            minIndex = placedIndex;
            // 内层不能从0开始
            for (int i = placedIndex + 1; i < length; i++) {
                if (nums[minIndex] > nums[i]) {
                    minIndex = i;
                }
            }
            swap(nums, placedIndex, minIndex);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};
        SelectionSort clazz = new SelectionSort();
        clazz.sortArray(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

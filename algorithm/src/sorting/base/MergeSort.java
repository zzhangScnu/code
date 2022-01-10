package sorting.base;

import java.util.Arrays;

/**
 * @author lihua
 * @since 2022/1/7
 */
public class MergeSort {

    public int[] sortArray(int[] nums) {
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private void sort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        // 第一次写的时候没有加上左索引值，这样求的只是数组一半的长度而已
        int middle = left + (right - left) / 2;
        sort(nums, left, middle);
        sort(nums, middle + 1, right);
        merge(nums, left, middle, right);
    }

    private void merge(int[] nums, int left, int middle, int right) {
        int[] leftNums = splitLeft(nums, left, middle);
        int[] rightNums = splitRightNums(nums, middle, right);
        int leftIndex = 0;
        int leftLength = leftNums.length;
        int rightIndex = 0;
        int rightLength = rightNums.length;
        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (leftNums[leftIndex] <= rightNums[rightIndex]) {
                // nums的开始索引不是0，因为归并排序的赋值是在原数组中操作的
                nums[left] = leftNums[leftIndex];
                leftIndex++;
            } else {
                nums[left] = rightNums[rightIndex];
                rightIndex++;
            }
            left++;
        }
        while (leftIndex < leftLength) {
            nums[left] = leftNums[leftIndex];
            leftIndex++;
            left++;
        }
        while (rightIndex < rightLength) {
            nums[left] = rightNums[rightIndex];
            rightIndex++;
            left++;
        }
    }

    private int[] splitLeft(int[] nums, int left, int middle) {
        // + 1是因为包含middle所在的值
        int length = middle - left + 1;
        int[] leftNums = new int[length];
        for (int i = 0; i < length; i++) {
            leftNums[i] = nums[left + i];
        }
        return leftNums;
    }

    private int[] splitRightNums(int[] nums, int middle, int right) {
        int length = right - middle;
        int[] rightNums = new int[length];
        for (int i = 0; i < length; i++) {
            // 这个索引值，左右两边减去i刚好就是middle + 1，原数组的起点索引
            rightNums[i] = nums[middle + 1 + i];
        }
        return rightNums;
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        MergeSort clazz = new MergeSort();
        clazz.sortArray(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

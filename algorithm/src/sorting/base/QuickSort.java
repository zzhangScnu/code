package sorting.base;

import java.util.Arrays;

// 912-排序数组
// sort-an-array
//给你一个整数数组 nums，请你将该数组升序排列。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [5,2,3,1]
//输出：[1,2,3,5]
//
//
// 示例 2：
//
//
//输入：nums = [5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 5 * 104
// -5 * 104 <= nums[i] <= 5 * 104
//
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序

/**
 * @author lihua
 * @since 2022/1/5
 */
public class QuickSort {

    public int[] quickSort(int[] arr) {
        doQuickSort(arr, 0, arr.length - 1);
        return arr;
    }

    public void doQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            doQuickSort(arr, low, partitionIndex - 1);
            doQuickSort(arr, partitionIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        for (int j = low; j <= high; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort clazz = new QuickSort();
        int[] arr = {5, 3, 4, 1, 2};
        clazz.quickSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

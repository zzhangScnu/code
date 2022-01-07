package sorting.base;

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

import java.util.Arrays;

/**
 * @author lihua
 * @since 2022/1/6
 */
public class HeapSort {

    public int[] sortArray(int[] nums) {
        int length = nums.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(nums, length, i);
        }
        // i既是要堆顶元素要被交换去的最后一个位置，也是交换后重新进行堆化的长度
        for (int i = length - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapify(nums, i, 0);
        }
        return nums;
    }

    private void heapify(int[] nums, int length, int rootIndex) {
        int leftChildIndex = rootIndex * 2 + 1;
        int rightChildIndex = rootIndex * 2 + 2;
        int largestIndex = rootIndex;
        // 要找到根、左孩子、右孩子之间最大的一个元素
        if (leftChildIndex < length && nums[leftChildIndex] > nums[largestIndex]) {
            largestIndex = leftChildIndex;
        }
        if (rightChildIndex < length && nums[rightChildIndex] > nums[largestIndex]) {
            largestIndex = rightChildIndex;
        }
        if (largestIndex != rootIndex) {
            swap(nums, largestIndex, rootIndex);
            // 被影响的子树，递归地堆化
            heapify(nums, length, largestIndex);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        HeapSort clazz = new HeapSort();
        clazz.sortArray(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

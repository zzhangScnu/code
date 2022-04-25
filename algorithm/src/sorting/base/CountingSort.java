package sorting.base;

import java.util.Arrays;

/**
 * @author lihua
 * @since 2022/1/24
 */
public class CountingSort {

    private int min;

    private int max;

    public int[] sortArray(int[] nums) {
        return doSort(nums);
    }

    private int[] doSort(int[] nums) {
        findMinAndMax(nums);
        // 用最大值减去最小值的差值作为数组的大小，防止大量空元素占用数组空间
        int[] sumArray = new int[max - min + 1];
        fillSumArray(nums, sumArray);
        return doSort(nums, sumArray);
    }

    private void findMinAndMax(int[] nums) {
        min = nums[0];
        max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
    }

    private void fillSumArray(int[] nums, int[] sumArray) {
        for (int num : nums) {
            // 这里必须要减去min（即偏移量）
            sumArray[num - min]++;
        }
        int length = sumArray.length;
        // 这番计算之后，sumArray[i]代表的就是【nums[i]减去偏移量的值】在整个序列里的排名
        // 类似前缀和
        for (int i = 1; i < length; i++) {
            sumArray[i] += sumArray[i - 1];
        }
    }

    private int[] doSort(int[] nums, int[] sumArray) {
        int length = nums.length;
        // 无法在原数组上直接排序
        int[] sorted = new int[length];
        int index;
        for (int i = length - 1; i >= 0; i--) {
            // 从后往前遍历原始数组，确定排序
            // 这样做是为了保证排序的稳定性
            index = sumArray[nums[i] - min]--;
            sorted[index - 1] = nums[i];
        }
        return sorted;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};
        CountingSort clazz = new CountingSort();
        int[] sortArr = clazz.sortArray(arr);
        Arrays.stream(sortArr).forEach(System.out::println);
    }
}

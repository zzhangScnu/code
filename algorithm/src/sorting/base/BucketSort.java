package sorting.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lihua
 * @since 2022/1/13
 */
public class BucketSort {

    private double min;

    private double interval;

    private int length;

    public double[] sortArray(double[] nums) {
        return doSort(nums);
    }

    private double[] doSort(double[] nums) {
        findMinAndMax(nums);
        length = nums.length;
        List<List<Double>> buckets = initializeBuckets();
        fillAndSort(nums, buckets);
        return assembleResult(buckets);
    }

    private double[] assembleResult(List<List<Double>> buckets) {
        double[] sortedArr = new double[length];
        int index = 0;
        for (List<Double> bucket : buckets) {
            for (double num : bucket) {
                sortedArr[index++] = num;
            }
        }
        return sortedArr;
    }

    private void fillAndSort(double[] nums, List<List<Double>> buckets) {
        int bucketIndex;
        for (double num : nums) {
            bucketIndex = calculateBucketIndex(num);
            buckets.get(bucketIndex).add(num);
        }
        for (int i = 0; i < length; i++) {
            Collections.sort(buckets.get(i));
        }
    }

    private List<List<Double>> initializeBuckets() {
        List<List<Double>> buckets = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            // 第一次，写成了addAll，导致桶容量为0，会报越界异常
            buckets.add(new LinkedList<>());
        }
        return buckets;
    }

    private int calculateBucketIndex(double num) {
        return (int) ((num - min) * (length - 1) / interval);
    }

    private void findMinAndMax(double[] nums) {
        min = nums[0];
        double max = nums[0];
        for (double num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        interval = max - min;
    }

    public static void main(String[] args) {
        double[] arr = {4.5, 0.84, 3.25, 2.18, 0.5};
        BucketSort clazz = new BucketSort();
        double[] sortArr = clazz.sortArray(arr);
        Arrays.stream(sortArr).forEach(System.out::println);
    }
}

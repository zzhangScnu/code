package base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * BD
 * <p>
 * 思路：从最高位开始比较
 * 如果高位小于目标数，则后面的位数全部取最大
 * 如果高位等于目标数，则第二高位也要小于等于目标数
 * 重复此过程
 *
 * @author lihua
 * @since 2022/3/29
 */
public class MaxNumInSet {

    private boolean equalFlag = true;

    private int index = -1;

    private Map<Integer, Integer> positionNumMap = new HashMap<>(16);

    public int findMaxNum(int[] nums, int n) {
        Arrays.sort(nums);
        initialize(n);
        return doFindMaxNum(nums);
    }

    private int doFindMaxNum(int[] nums) {
        int result = 0;
        while (index >= 0) {
            Integer bit = positionNumMap.get(index);
            int bitResult = equalFlag ? findFirstSmallerOrEqualNum(nums, bit) : findLargestNum(nums);
            equalFlag = bitResult == bit;
            result = result + (int) Math.pow(10, index) * bitResult;
            index--;
        }
        return result;
    }

    private int initialize(int n) {
        while (n > 0) {
            positionNumMap.put(++index, n % 10);
            n /= 10;
        }
        return index;
    }

    private int findFirstSmallerOrEqualNum(int[] nums, int target) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        int mid;
        int midNum;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            midNum = nums[mid];
            if (midNum > target) {
                high = mid - 1;
            } else {
                if (mid == length - 1 || nums[mid + 1] > target) {
                    return midNum;
                } else {
                    low = mid + 1;
                }
            }
        }
        return 0;
    }

    private int findLargestNum(int[] nums) {
        return nums[nums.length - 1];
    }

    public static void main(String[] args) {
        MaxNumInSet maxNumInSet = new MaxNumInSet();
        int[] nums = new int[]{2, 3, 9};
        int firstSmallerOrEqualNum = maxNumInSet.findFirstSmallerOrEqualNum(nums, 5);
        assert firstSmallerOrEqualNum == 3;
        System.out.println(firstSmallerOrEqualNum);
        int maxNum = maxNumInSet.findMaxNum(nums, 24123);
        assert maxNum == 23999;
        System.out.println(maxNum);
    }
}

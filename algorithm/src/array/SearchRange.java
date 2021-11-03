package array;
// 34-在排序数组中查找元素的第一个和最后一个位置
// find-first-and-last-position-of-element-in-sorted-array
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。
//
// 进阶：
//
//
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
//
//
//
//
// 示例 1：
//
//
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4]
//
// 示例 2：
//
//
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1]
//
// 示例 3：
//
//
//输入：nums = [], target = 0
//输出：[-1,-1]
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 105
// -109 <= nums[i] <= 109
// nums 是一个非递减数组
// -109 <= target <= 109
//
// Related Topics 数组 二分查找

/**
 * @author lihua
 * @since 2021/11/3
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        int lRange = searchLeft(nums, target);
        int rRange = searchRight(nums, target);
        return new int[]{lRange, rRange};
    }

    private int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int middle;
        while (left <= right) {
            middle = (left + right) / 2;
            // 收缩右界，目标是当left = right时，锁定左界
            if (target == nums[middle]) {
                right = middle - 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            }
        }
        // 1. 如果搜索到最后（此时left = right + 1），nums[left]都不等于target，表示一个都没找到
        // 2.当target比nums中的所有数字都要大，left会走到nums.length的位置，要防止数组越界
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    private int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int middle;
        while (left <= right) {
            middle = (left + right) / 2;
            // 收缩左界，目标是当left = right时，锁定右界
            if (target == nums[middle]) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            }
        }
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        // 这里因为结束条件为left = right + 1，即跳出循环的时候left多加了1，所以是比right大1的
        return right;
    }

    public static void main(String[] args) {
        SearchRange searchRange = new SearchRange();
        int[] results = searchRange.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 15);
        // [3, 4]
        assert results.length == 2;
    }
}

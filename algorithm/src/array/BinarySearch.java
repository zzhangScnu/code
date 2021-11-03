package array;
// 704-二分查找
// binary-search
//给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否
//则返回 -1。
//
//
//示例 1:
//
// 输入: nums = [-1,0,3,5,9,12], target = 9
//输出: 4
//解释: 9 出现在 nums 中并且下标为 4
//
//
// 示例 2:
//
// 输入: nums = [-1,0,3,5,9,12], target = 2
//输出: -1
//解释: 2 不存在 nums 中因此返回 -1
//
//
//
//
// 提示：
//
//
// 你可以假设 nums 中的所有元素是不重复的。
// n 将在 [1, 10000]之间。
// nums 的每个元素都将在 [-9999, 9999]之间。
//
// Related Topics 数组 二分查找

/**
 * 如果right = nums.length，搜索区间为[left, right)，左闭右开
 * 那么while中的判断条件为(left < right)
 *
 * @author lihua
 * @since 2021/11/3
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        // 搜索区间为[left, right]，全闭区间
        int left = 0;
        int right = nums.length - 1;
        int middle;
        // 如果用<，终止条件为left = right，left / right指向的元素会被漏掉
        // 如果用<=，终止条件为left = right + 1
        // 判断条件可以代入具体数值，看看区间内是否合法
        while (left <= right) {
            middle = (left + right) / 2;
            // 必须三条分支都判断，不然没有目标元素时，无法得出-1
            if (target == nums[middle]) {
                return middle;
            }
            if (target < nums[middle]) {
                // 右界缩减1
                right = middle - 1;
                continue;
            }
            // 左界缩减1
            if (target > nums[middle]) {
                left = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int result = binarySearch.search(new int[]{-1, 0, 3, 5, 9, 12}, 2);
        assert result == -1;
    }
}

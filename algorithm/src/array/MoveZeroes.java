package array;
// 283-移动零
// move-zeroes
//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
// 说明:
//
//
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
//
// Related Topics 数组 双指针

/**
 * @author lihua
 * @since 2021/11/11
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int zeroThreshold = removeZeros(nums);
        int length = nums.length;
        for (int i = zeroThreshold; i < length; i++) {
            nums[i] = 0;
        }
    }

    private int removeZeros(int[] nums) {
        int length = nums.length;
        int slow = 0, fast = 0;
        while (fast < length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}

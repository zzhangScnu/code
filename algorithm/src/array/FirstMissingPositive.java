package array;

// 41-缺失的第一个正数
//
//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
//请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,0]
//输出：3
//
//
// 示例 2：
//
//
//输入：nums = [3,4,-1,1]
//输出：2
//
//
// 示例 3：
//
//
//输入：nums = [7,8,9,11,12]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 5 * 10⁵
// -2³¹ <= nums[i] <= 2³¹ - 1
//

/**
 * @author lihua
 * @since 2022/2/7
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            // 如果没有缺失正整数的话，数组下标和元素值的对应关系是：i = nums[i] - 1
            // not if, while
            // 这里要用值去对比，不能用索引i != nums[i] - 1来对比。否则处理不了诸如 [1, 1] 的情况。TODO: WHY?
            while (nums[i] > 0 && nums[i] <= length && nums[i] != nums[nums[i] - 1]) {
                // 将i的元素，交换到它应该在的正确下标(nums[i] - 1)上
                swap(nums, i, nums[i] - 1);
                // 交换完之后，继续看i对应的、刚刚从后面被置换过来的元素，是否满足坑位条件
            }
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return length + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        FirstMissingPositive clazz = new FirstMissingPositive();
        int result = clazz.firstMissingPositive(new int[]{1, 1});
        assert result == 2;
    }
}

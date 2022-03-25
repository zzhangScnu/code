package array;

// 4-寻找两个正序数组的中位数
// median-of-two-sorted-arrays
//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
// 算法的时间复杂度应该为 O(log (m+n)) 。
//
//
//
// 示例 1：
//
//
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
//
//
// 示例 2：
//
//
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
//
//
//
//
//
//
// 提示：
//
//
// nums1.length == m
// nums2.length == n
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -106 <= nums1[i], nums2[i] <= 106
//
// Related Topics 数组 二分查找 分治

/**
 * @author lihua
 * @since 2022/3/21
 */
public class FindMedianSortedArrays {

    /**
     * i和j，表示大数组中，中位数左侧的两个小数组的【末尾元素的下一个元素】
     * <p>
     * i + j = (nums1.length + nums2.length + 1) / 2
     * max(nums1[i], nums2[j]) >= max(num1[i - 1], nums2[j - 1])
     * <p>
     * 中位数 =
     * if 奇数： nums[i - 1]
     * if 偶数： (nums[i - 1] + nums[j]) / 2
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int low = 0;
        int high = length1 - 1;
        int i, j;
        int maxLeft, maxRight;
        while (low <= high) {
            i = low + ((high - low) >> 1);
            j = ((length1 + length2 + 1) >> 1) - i;
            maxRight = Math.max(nums1[i], nums2[j]);
            maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
            if (maxLeft > maxRight) {
                low = i - 1;
            } else if (maxLeft <= maxRight) {

            }


        }
        return -1;
    }

    public static void main(String[] args) {
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2, 4, 5};
        double median = findMedianSortedArrays.findMedianSortedArrays(nums1, nums2);
        System.out.println(median);
        assert Math.abs(median - 2.5d) < 0.1;
    }
    // 1, 2, [3], 4, 5
    // i = 2, j = 1
}

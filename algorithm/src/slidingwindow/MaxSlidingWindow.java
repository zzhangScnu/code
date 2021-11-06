package slidingwindow;
// 239-滑动窗口最大值
// sliding-window-maximum
//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。
//
// 返回滑动窗口中的最大值。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//
// 示例 2：
//
//
//输入：nums = [1], k = 1
//输出：[1]
//
//
// 示例 3：
//
//
//输入：nums = [1,-1], k = 1
//输出：[1,-1]
//
//
// 示例 4：
//
//
//输入：nums = [9,11], k = 2
//输出：[11]
//
//
// 示例 5：
//
//
//输入：nums = [4,-2], k = 2
//输出：[4]
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 105
// -104 <= nums[i] <= 104
// 1 <= k <= nums.length
//
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列）

import java.util.PriorityQueue;

/**
 * n个数，k大小的滑动窗口，会有n-k+1个滑动窗口
 *
 * @author lihua
 * @since 2021/11/6
 */
public class MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;
        int[] results = new int[length - k + 1];
        // 用大根堆实现
        PriorityQueue<int[]> queue = new PriorityQueue<>((arr1, arr2) -> arr2[0] - arr1[0]);
        // 预先初始化第一个滑动窗口的元素进堆中，后续每次只处理一个元素，即不断向右滑动
        for (int i = 0; i < k; i++) {
            queue.add(new int[]{nums[i], i});
        }
        // 这里是peek而不是poll：每次取最大值，都不应该从堆中移除
        results[0] = queue.peek()[0];
        for (int i = k; i < length; i++) {
            queue.add(new int[]{nums[i], i});
            // 当堆顶元素已经不在滑动窗口中时，把它移除
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            // 此时仍在堆顶的元素，即为当前滑动窗口中最大的元素
            results[i - k + 1] = queue.peek()[0];
        }
        return results;
    }

    public static void main(String[] args) {
        MaxSlidingWindow clazz = new MaxSlidingWindow();
        int[] results = clazz.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        assert results.length == 6;
    }
}

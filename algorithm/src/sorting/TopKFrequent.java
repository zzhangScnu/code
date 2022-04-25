package sorting;// 347-前 K 个高频元素
// top-k-frequent-elements
//给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
//
//
//
// 示例 1:
//
//
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
//
//
// 示例 2:
//
//
//输入: nums = [1], k = 1
//输出: [1]
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 105
// k 的取值范围是 [1, 数组中不相同的元素的个数]
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
//
//
//
//
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列）

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 可以在大根堆中存储一个数组/对象，同时放入数字和出现的次数，
 * 这样只需要k（n？）次比较放入大根堆，而不是全部放入再取前k个
 *
 * @author lihua
 * @since 2021/11/3
 */
public class TopKFrequent {

    public int[] topKFrequent(int[] nums, int k) {
        // 不能用数组，因为有可能有负数
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            // 下标：数字 值：出现次数
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        // 根据出现次数给大根堆排序
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> frequency.get(o2) - frequency.get(o1));
        queue.addAll(frequency.keySet());
        int[] results = new int[k];
        int i = 0;
        while (!queue.isEmpty()) {
            results[i++] = queue.remove();
        }
        return results;
    }

    public static void main(String[] args) {
        TopKFrequent clazz = new TopKFrequent();
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] results = clazz.topKFrequent(nums, k);
        assert results.length == 2;
    }
}

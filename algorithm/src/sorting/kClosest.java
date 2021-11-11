package sorting;
// 973-最接近原点的 K 个点
// k-closest-points-to-origin
//我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
//
// （这里，平面上两点之间的距离是欧几里德距离。）
//
// 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
//
//
//
// 示例 1：
//
// 输入：points = [[1,3],[-2,2]], K = 1
//输出：[[-2,2]]
//解释：
//(1, 3) 和原点之间的距离为 sqrt(10)，
//(-2, 2) 和原点之间的距离为 sqrt(8)，
//由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
//我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
//
//
// 示例 2：
//
// 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
//输出：[[3,3],[-2,4]]
//（答案 [[-2,4],[3,3]] 也会被接受。）
//
//
//
//
// 提示：
//
//
// 1 <= K <= points.length <= 10000
// -10000 < points[i][0] < 10000
// -10000 < points[i][1] < 10000
//
// Related Topics 几何 数组 数学 分治 快速选择 排序 堆（优先队列）

import java.util.PriorityQueue;

/**
 * 改进：
 * 1. 可以用长度为2的数组代替自定义类
 * 2. 循环可以分两步，先入k个元素，再边判断边入后n-k个元素，这样不用每次都判断一下堆的大小
 *
 * @author lihua
 * @since 2021/11/11
 */
public class kClosest {

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Element> descQueue = new PriorityQueue<>(k, (o1, o2) -> o2.distanceIn2Squared - o1.distanceIn2Squared);
        int distanceIn2Squared;
        Element top;
        for (int[] point : points) {
            distanceIn2Squared = point[0] * point[0] + point[1] * point[1];
            if (descQueue.size() < k) {
                descQueue.add(new Element(point, distanceIn2Squared));
            } else {
                top = descQueue.peek();
                if (top.getDistanceIn2Squared() > distanceIn2Squared) {
                    descQueue.remove();
                    descQueue.add(new Element(point, distanceIn2Squared));
                }
            }
        }
        int[][] results = new int[k][2];
        for (int i = 0; i < k; i++) {
            results[i] = descQueue.remove().getPoint();
        }
        return results;
    }

    public static class Element {

        private int[] point;

        private int distanceIn2Squared;

        public Element(int[] point, int distance) {
            this.point = point;
            this.distanceIn2Squared = distance;
        }

        public int[] getPoint() {
            return point;
        }

        public int getDistanceIn2Squared() {
            return distanceIn2Squared;
        }
    }

    public static void main(String[] args) {
        kClosest clazz = new kClosest();
        int[][] points = new int[][]{{3, 3}, {5, -1}, {-2, 4}};
        int[][] results = clazz.kClosest(points, 2);
        assert results.length == 2;
    }
}

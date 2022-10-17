package src.design;

import java.util.*;

//给你一个二维数组 tasks ，用于表示 n 项从 0 到 n - 1 编号的任务。其中 tasks[i] = [enqueueTimei,
//processingTimei] 意味着第 i 项任务将会于 enqueueTimei 时进入任务队列，需要 processingTimei 的时长完成执行。
//
// 现有一个单线程 CPU ，同一时间只能执行 最多一项 任务，该 CPU 将会按照下述方式运行：
//
//
// 如果 CPU 空闲，且任务队列中没有需要执行的任务，则 CPU 保持空闲状态。
// 如果 CPU 空闲，但任务队列中有需要执行的任务，则 CPU 将会选择 执行时间最短 的任务开始执行。如果多个任务具有同样的最短执行时间，则选择下标最小的
//任务开始执行。
// 一旦某项任务开始执行，CPU 在 执行完整个任务 前都不会停止。
// CPU 可以在完成一项任务后，立即开始执行一项新任务。
//
//
// 返回 CPU 处理任务的顺序。
//
//
//
// 示例 1：
//
// 输入：tasks = [[1,2],[2,4],[3,2],[4,1]]
//输出：[0,2,3,1]
//解释：事件按下述流程运行：
//- time = 1 ，任务 0 进入任务队列，可执行任务项 = {0}
//- 同样在 time = 1 ，空闲状态的 CPU 开始执行任务 0 ，可执行任务项 = {}
//- time = 2 ，任务 1 进入任务队列，可执行任务项 = {1}
//- time = 3 ，任务 2 进入任务队列，可执行任务项 = {1, 2}
//- 同样在 time = 3 ，CPU 完成任务 0 并开始执行队列中用时最短的任务 2 ，可执行任务项 = {1}
//- time = 4 ，任务 3 进入任务队列，可执行任务项 = {1, 3}
//- time = 5 ，CPU 完成任务 2 并开始执行队列中用时最短的任务 3 ，可执行任务项 = {1}
//- time = 6 ，CPU 完成任务 3 并开始执行任务 1 ，可执行任务项 = {}
//- time = 10 ，CPU 完成任务 1 并进入空闲状态
//
//
// 示例 2：
//
// 输入：tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
//输出：[4,3,2,0,1]
//解释：事件按下述流程运行：
//- time = 7 ，所有任务同时进入任务队列，可执行任务项  = {0,1,2,3,4}
//- 同样在 time = 7 ，空闲状态的 CPU 开始执行任务 4 ，可执行任务项 = {0,1,2,3}
//- time = 9 ，CPU 完成任务 4 并开始执行任务 3 ，可执行任务项 = {0,1,2}
//- time = 13 ，CPU 完成任务 3 并开始执行任务 2 ，可执行任务项 = {0,1}
//- time = 18 ，CPU 完成任务 2 并开始执行任务 0 ，可执行任务项 = {1}
//- time = 28 ，CPU 完成任务 0 并开始执行任务 1 ，可执行任务项 = {}
//- time = 40 ，CPU 完成任务 1 并进入空闲状态
//
//
//
// 提示：
//
//
// tasks.length == n
// 1 <= n <= 10⁵
// 1 <= enqueueTimei, processingTimei <= 10⁹
//
//
// Related Topics 数组 排序 堆（优先队列）
public class SingleThreadedCPU {

    public int[] getOrder(int[][] tasks) {
        List<int[]> tasksInFuture = new ArrayList<>(); // 控制时间线。使得在队列中的任务都是可以执行的，不需要做额外判断
        int len = tasks.length;
        for (int i = 0; i < len; i++) {
            tasksInFuture.add(new int[]{tasks[i][0], tasks[i][1], i}); // 开始时间，持续时长，索引位置
        }
        tasksInFuture.sort(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return a[2] - b[2];
            }
            return a[1] - b[1];
        });
        int[] res = new int[len];
        int idx = 0;
        int now = 0;
        while (idx < len) {
            if (!queue.isEmpty()) { // 队列里的任务一定是当前可执行的
                int[] task = queue.poll();
                res[idx++] = task[2];
                now += task[1];
            } else if (tasksInFuture.size() > 0) { // 队列空的时候，当前时间直接跃迁到下一个可执行任务，以填充队列
                now = tasksInFuture.get(0)[0];
            }
            while (tasksInFuture.size() > 0 && tasksInFuture.get(0)[0] <= now) {
                queue.add(tasksInFuture.remove(0));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SingleThreadedCPU cpu = new SingleThreadedCPU();
        int[][] tasks = {{1, 2}, {2, 4}, {3, 2}, {4, 1}};
        int[] res = cpu.getOrder(tasks); // 0,2,3,1
        for (int i : res) {
            System.out.println(i);
        }
    }
}

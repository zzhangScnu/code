package src.design;

//实现RandomizedSet 类：
//
//
//
//
// RandomizedSet() 初始化 RandomizedSet 对象
// bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
// bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
// int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
//
//
//
//
// 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
//
//
//
// 示例：
//
//
//输入
//["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove",
//"insert", "getRandom"]
//[[], [1], [2], [2], [], [1], [2], []]
//输出
//[null, true, false, true, 2, true, false, 2]
//
//解释
//RandomizedSet randomizedSet = new RandomizedSet();
//randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
//randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
//randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
//randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
//randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
//randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
//randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
//
//
//
//
// 提示：
//
//
// -2³¹ <= val <= 2³¹ - 1
// 最多调用 insert、remove 和 getRandom 函数 2 * 10⁵ 次
// 在调用 getRandom 方法时，数据结构中 至少存在一个 元素。
//
//
// Related Topics 设计 数组 哈希表 数学 随机化

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class RandomizedSet {

    private List<Integer> keys;

    private HashMap<Integer, Integer> map; // key to index

    public RandomizedSet() {
        keys = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        keys.add(val);
        map.put(val, keys.size()-1);
        return true;
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int idx = map.get(val);
            swapToEnd(idx);
            keys.remove(keys.size() - 1);
            map.remove(val);
            return true;
        }
        return false;
    }

    private void swapToEnd(int idx) {
        int lastIdx = keys.size() - 1;
        int removedVal = keys.get(idx);
        int swappedVal = keys.get(lastIdx);
        keys.set(idx, keys.get(lastIdx));
        keys.set(lastIdx, removedVal);
        map.put(swappedVal, idx);
    }

    public int getRandom() {
        int idx = new Random().nextInt(0, keys.size());
        return keys.get(idx);
    }

    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        set.insert(0);
        set.insert(1);
        set.remove(0);
        set.insert(2);
        set.remove(1);
        System.out.println(set.getRandom());
    }
}

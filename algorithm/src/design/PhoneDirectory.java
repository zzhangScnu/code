package src.design;

import java.util.HashMap;
import java.util.HashSet;

//设计一个电话目录管理系统，让它支持以下功能：
//
//get: 分配给用户一个未被使用的电话号码，获取失败请返回 -1
//
//check: 检查指定的电话号码是否被使用
//
//release: 释放掉一个电话号码，使其能够重新被分配
public class PhoneDirectory {

    private HashSet<Integer> set;

    public PhoneDirectory(int n) {
        set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(i);
        }
    }

    public int get() {
        if (set.isEmpty()) {
            return -1;
        }
        int number = set.iterator().next();
        set.remove(number);
        return number;
    }

    public void release(int number) {
        set.add(number);
    }

    public boolean check(int number) {
        return set.contains(number);
    }


    public static void main(String[] args) {
        // 初始化电话目录，包括 3 个电话号码：0，1 和 2。
        PhoneDirectory directory = new PhoneDirectory(3);

        // 可以返回任意未分配的号码，这里我们假设它返回 0。
        System.out.println(directory.get());

        // 假设，函数返回 1。
        System.out.println(directory.get());

        // 号码 2 未分配，所以返回为 true。
        System.out.println(directory.check(2));

        // 返回 2，分配后，只剩一个号码未被分配。
        System.out.println(directory.get());

        // 此时，号码 2 已经被分配，所以返回 false。
        System.out.println(directory.check(2));

        // 释放号码 2，将该号码变回未分配状态。
        directory.release(2);

        // 号码 2 现在是未分配状态，所以返回 true。
        System.out.println(directory.check(2));
    }
}

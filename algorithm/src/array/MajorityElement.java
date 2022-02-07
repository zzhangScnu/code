package array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lihua
 * @since 2022/2/7
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        int maxNum = 0;
        int maxCount = 0;
        Map<Integer, Integer> counter = new HashMap<>(16);
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
            if (counter.get(num) > maxCount) {
                maxCount = counter.get(num);
                maxNum = num;
            }
        }
        return maxNum;
    }

    public static void main(String[] args) {
        MajorityElement clazz = new MajorityElement();
        int result = clazz.majorityElement(new int[]{6, 5, 5});
        assert result == 5;
    }
}

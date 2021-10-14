import java.util.HashMap;
import java.util.Map;

/**
 * @author lihua
 * @since 2021/10/14
 */
public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		// target -> 其中一个num的数组下标
		Map<Integer, Integer> resultMap = new HashMap<>(128);
		int length = nums.length;
		int[] resultIndexArr = new int[2];
		for (int index = 0; index < length; index++) {
			int num = nums[index];
			int findNum = target - num;
			if (resultMap.containsKey(findNum)) {
				int findIndex = resultMap.get(findNum);
				resultIndexArr[0] = findIndex;
				resultIndexArr[1] = index;
				break;
			}
			resultMap.put(num, index);
		}
		return resultIndexArr;
	}
}

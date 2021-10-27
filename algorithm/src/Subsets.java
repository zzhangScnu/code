import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lihua
 * @since 2021/10/27
 */
public class Subsets {

    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, 0, track);
        return resultList;
    }

    private void backtrack(int[] nums, int index, LinkedList<Integer> track) {
        // 遍历完整个数组之后，就可以将路径放进结果集了
        if (index == nums.length) {
            resultList.add(new LinkedList<>(track));
            return;
        }
        // 选择index所在位置的元素的情况
        track.add(nums[index]);
        backtrack(nums, index + 1, track);
        track.removeLast();
        // 不选择index所在位置的元素的情况
        backtrack(nums, index + 1, track);
    }

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> resultList = subsets.subsets(nums);
        assert resultList.size() == 8;
    }
}

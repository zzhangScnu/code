import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author lihua
 * @since 2021/10/27
 */
public class Subsets {

    private Set<List<Integer>> resultList = new HashSet<>();

    public List<List<Integer>> subsets(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        resultList.add(new LinkedList<>());
        backtrack(nums, track);
        return new ArrayList<>(resultList);
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        if (nums.length == track.size()) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                // 由于不能有重复元素，已加入的就不能再选择了
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track);
            resultList.add(new LinkedList<>(track));
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> resultList = subsets.subsets(nums);
        assert resultList.size() == 8;
    }
}

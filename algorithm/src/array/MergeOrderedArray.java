package array;

/**
 * 两个有序数组合并为一个有序数组
 *
 * @author lihua
 * @since 2022/2/5
 */
public class MergeOrderedArray {

    public int[] merge(int[] arr1, int[] arr2) {
        int length1 = arr1.length;
        int length2 = arr2.length;
        int index = 0;
        int index1 = 0;
        int index2 = 0;
        int[] result = new int[length1 + length2];
        while (index1 < length1 && index2 < length2) {
            if (arr1[index1] <= arr2[index2]) {
                result[index++] = arr1[index1++];
            } else {
                result[index++] = arr2[index2++];
            }
        }
        while (index1 < length1) {
            result[index++] = arr1[index1++];
        }
        while (index2 < length2) {
            result[index++] = arr2[index2++];
        }
        return result;
    }

    public static void main(String[] args) {
        MergeOrderedArray clazz = new MergeOrderedArray();
        int[] arr1 = new int[]{1, 3, 5, 7, 9};
        int[] arr2 = new int[]{2, 4, 6, 8, 10};
        int[] result = clazz.merge(arr1, arr2);
        assert result.length == 10;
    }
}

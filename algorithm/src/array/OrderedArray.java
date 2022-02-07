package array;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 实现一个大小固定的有序数组，支持动态增删改操作
 *
 * @author lihua
 * @since 2022/2/5
 */
public class OrderedArray {

    public static class Array {

        private final int[] arr;

        private int count;

        public Array(int capacity) {
            arr = new int[capacity];
            count = 0;
        }

        public void add(int data) {
            checkNotFull();
            int firstGreaterIndex = findIndex(data);
            moveOut(firstGreaterIndex);
            arr[firstGreaterIndex] = data;
            count++;
        }

        private void moveOut(int pivot) {
            for (int i = arr.length - 1; i >= pivot ; i--) {
                if (i == 0) {
                    break;
                }
                arr[i] = arr[i - 1];
            }
        }

        private void checkNotFull() {
            if (count == arr.length) {
                throw new IllegalStateException("reach max size of: " + count);
            }
        }

        /**
         * 二分查找
         *
         * @param data 被查找的数据
         * @return 第一个值大于等于data的索引
         */
        private int findIndex(int data) {
            int low = 0;
            int high = arr.length - 1;
            int mid;
            while (low <= high) {
                mid = low + ((high - low) >> 1);
                if (data > arr[mid]) {
                    low = mid + 1;
                } else {
                    if (mid == 0 || arr[mid - 1] < data) {
                        return mid;
                    } else {
                        high = mid - 1;
                    }
                }
            }
            return 0;
        }

        public void deleteData(int data) {
            int index = findIndex(data);
            deleteAt(index);
        }

        private void moveIn(int pivot) {
            for (int i = pivot; i < count - 1; i++) {
                arr[i] = arr[i + 1];
            }
        }

        public void deleteAt(int index) {
            checkValidIndex(index);
            moveIn(index);
            // count保证了不会读取到有效数据之外的值
            count--;
        }

        public void put(int index, int data) {
            checkValidIndex(index);
            arr[index] = data;
        }

        private void checkValidIndex(int index) {
            if (index >= count) {
                throw new IllegalStateException("exceeded current max data size of: " + count);
            }
        }

        public int get(int index) {
            checkValidIndex(index);
            return arr[index];
        }

        public int find(int data) {
            // todo：有序数组的二分查找
            return -1;
        }

        @Override
        public String toString() {
            return Arrays.stream(arr)
                    .limit(count)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(","));
        }
    }

    public static void main(String[] args) {
        Array clazz = new Array(4);
        clazz.add(4);
        clazz.add(4);
        clazz.add(2);
        clazz.add(1);
        // clazz.add(3); throws IllegalStateException
        clazz.deleteData(4);
        clazz.deleteAt(0);
        int result = clazz.get(0);
        assert result == 2;
        // 2, 4
        System.out.println(clazz);
    }
}

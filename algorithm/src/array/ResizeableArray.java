package array;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 支持动态扩容的数组
 *
 * @author lihua
 * @since 2022/2/4
 */
public class ResizeableArray {

    public static class Array {

        private Object[] originData;

        private Object[] resizedData;

        private int capacity;

        private final double factor;

        private int threshold;

        private int currentSize;

        private int resizeIndex = -1;

        public Array(int capacity) {
            this(capacity, 0.75);
        }

        public Array(int capacity, double factor) {
            this.capacity = capacity;
            this.factor = factor;
            this.threshold = (int) (capacity * factor);
            this.currentSize = 0;
            this.originData = new Object[capacity];
        }

        public Object get(int i) {
            if (i >= originData.length) {
                rehash();
                return resizedData[i];
            }
            // 如果用Optional的话，因为resizedData可能会是null，就有可能在orElse出现空指针异常
            Object result = originData[i] != null ? originData[i] : resizedData[i];
            rehash();
            return result;
        }

        public void put(int i, Object data) {
            if (currentSize < threshold) {
                originData[i] = data;
                currentSize++;
                return;
            }
            resize();
            resizedData[i] = data;
            currentSize++;
            rehash();
        }

        public void resize() {
            if (resizeIndex != -1) {
                return;
            }
            capacity = 2 * capacity;
            this.threshold = (int) (capacity * factor);
            resizedData = new Object[capacity];
            resizeIndex = 0;
        }

        public void rehash() {
            if (resizeIndex == -1) {
                return;
            }
            resizedData[resizeIndex] = originData[resizeIndex];
            originData[resizeIndex] = null;
            resizeIndex++;
            if (resizeIndex == originData.length - 1) {
                resizeIndex = -1;
                this.originData = this.resizedData;
                this.resizedData = null;
            }
        }

        public int size() {
            return Optional.ofNullable(resizedData)
                    .map(resizedData -> resizedData.length)
                    .orElse(originData.length);
        }

        @Override
        public String toString() {
            String originDataStr = convert(originData);
            String resizedDataStr = convert(resizedData);
            return String.format("originData: %s; resizedData: %s", originDataStr, resizedDataStr);
        }

        private String convert(Object[] arr) {
            if (arr == null) {
                return "null";
            }
            return Arrays.stream(arr)
                    // 如果强转，可能会报ClassCastException
                    .map(data -> Optional.ofNullable(data)
                            .map(Object::toString)
                            .orElse("null"))
                    .collect(Collectors.joining(","));
        }
    }

    public static void main(String[] args) {
        Array resizeableArray = new Array(4);
        for (int i = 0; i < 4; i++) {
            resizeableArray.put(i, i);
            System.out.printf("第%s次放入后，大小为%s%n", i + 1, resizeableArray.size());
        }
        for (int i = 0; i < 4; i++) {
            resizeableArray.get(i);
            // 这里不需要显式调用toString
            System.out.printf("第%s次取出后，数组内容为%s%n", i + 1, resizeableArray);
        }
    }
}

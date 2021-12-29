package multiplethread;

/**
 * @author lihua
 * @since 2021/12/29
 */
public interface Printer {

    void printOdd(int num);

    void printEven(int num);

    default boolean isOdd(int num) {
        return num % 2 != 0;
    }
}

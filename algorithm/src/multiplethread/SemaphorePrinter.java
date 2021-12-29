package multiplethread;

import java.util.concurrent.Semaphore;

/**
 * @author lihua
 * @since 2021/12/29
 */
public class SemaphorePrinter implements Printer {

    private Semaphore oddSemaphore = new Semaphore(1);

    private Semaphore evenSemaphore = new Semaphore(0);

    @Override
    public void printOdd(int num) {
        try {
            oddSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println(String.format("%s: %s", Thread.currentThread().getName(), num));
        evenSemaphore.release();
    }

    @Override
    public void printEven(int num) {
        try {
            evenSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println(String.format("%s: %s", Thread.currentThread().getName(), num));
        oddSemaphore.release();
    }
}

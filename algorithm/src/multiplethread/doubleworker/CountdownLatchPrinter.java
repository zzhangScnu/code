package multiplethread.doubleworker;

import java.util.concurrent.CountDownLatch;

/**
 * @author lihua
 * @since 2021/12/29
 */
public class CountdownLatchPrinter implements Printer {

    /**
     * countDownLatch在使用后无法复位，是一次性的
     * 所以无法像semaphore一样归还信号量，以便下一次循环使用
     * 所以这个示例其实是先打了50个奇数，再打了50个偶数
     */
    private CountDownLatch oddLatch = new CountDownLatch(0);

    private CountDownLatch evenLatch = new CountDownLatch(50);

    @Override
    public void printOdd(int num) {
        try {
            oddLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println(String.format("%s: %s", Thread.currentThread().getName(), num));
        evenLatch.countDown();
    }

    @Override
    public void printEven(int num) {
        try {
            evenLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println(String.format("%s: %s", Thread.currentThread().getName(), num));
        oddLatch.countDown();
    }
}

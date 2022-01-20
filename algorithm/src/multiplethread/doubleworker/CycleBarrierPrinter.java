package multiplethread.doubleworker;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lihua
 * @since 2021/12/29
 */
public class CycleBarrierPrinter implements Printer {

    /**
     * 自动归位
     */
    private CyclicBarrier barrier = new CyclicBarrier(1);

    private boolean printOdd;

    public CycleBarrierPrinter(int start) {
        this.printOdd = isOdd(start);
    }

    @Override
    public void printOdd(int num) {
        while (!printOdd) {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(String.format("%s: %s", Thread.currentThread().getName(), num));
        printOdd = false;
    }

    @Override
    public void printEven(int num) {
        while (printOdd) {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(String.format("%s: %s", Thread.currentThread().getName(), num));
        printOdd = true;
    }
}

package multiplethread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lihua
 * @since 2021/12/29
 */
public class ReentrantLockPrinter implements Printer {

    private volatile boolean printOdd;

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public ReentrantLockPrinter(int start) {
        this.printOdd = isOdd(start);
    }

    @Override
    public void printOdd(int num) {
        lock.lock();
        try {
            while (!printOdd) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("%s: %s", Thread.currentThread().getName(), num));
            printOdd = false;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void printEven(int num) {
        lock.lock();
        try {
            while (printOdd) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("%s: %s", Thread.currentThread().getName(), num));
            printOdd = true;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

package multiplethread.tripleworker;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author lihua
 * @since 2022/1/20
 */
public class ConditionPrinter extends Thread {

    private final String content;

    private final Lock lock;

    private final Condition current;

    private final Condition next;

    public ConditionPrinter(String content, Lock lock, Condition current, Condition next) {
        this.content = content;
        this.lock = lock;
        this.current = current;
        this.next = next;
    }

    @Override
    public void run() {
        int i = 10;
        while (i >= 0) {
            lock.lock();
            try {
                if (i == 0) {
                    next.signal();
                    break;
                }
                System.out.println(content);
                i--;
                next.signal();
                current.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("exception: " + e);
            } finally {
                lock.unlock();
            }
        }
    }
}

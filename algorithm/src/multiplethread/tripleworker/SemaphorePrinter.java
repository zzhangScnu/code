package multiplethread.tripleworker;

import java.util.concurrent.Semaphore;

/**
 * @author lihua
 * @since 2022/1/20
 */
public class SemaphorePrinter extends Thread {

    private final String content;

    private final Semaphore current;

    private final Semaphore next;

    public SemaphorePrinter(String content, Semaphore current, Semaphore next) {
        this.content = content;
        this.current = current;
        this.next = next;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                current.acquire();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("exception: " + e);
            }
            System.out.println(content);
            next.release();
        }
    }
}
